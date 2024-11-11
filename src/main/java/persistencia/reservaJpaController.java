/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import javax.persistence.Persistence;
import logica.usuario;
import logica.cancha;
import logica.reserva;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author lauta
 */
public class reservaJpaController implements Serializable {
    
    public reservaJpaController(){
        emf = Persistence.createEntityManagerFactory("PracticaWebPrueba_PU");
    }

    public reservaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(reserva reserva) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuario usuario = reserva.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                reserva.setUsuario(usuario);
            }
            cancha cancha = reserva.getCancha();
            if (cancha != null) {
                cancha = em.getReference(cancha.getClass(), cancha.getId());
                reserva.setCancha(cancha);
            }
            em.persist(reserva);
            if (usuario != null) {
                usuario.getReservas().add(reserva);
                usuario = em.merge(usuario);
            }
            if (cancha != null) {
                cancha.getReservas().add(reserva);
                cancha = em.merge(cancha);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(reserva reserva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reserva persistentreserva = em.find(reserva.class, reserva.getId());
            usuario usuarioOld = persistentreserva.getUsuario();
            usuario usuarioNew = reserva.getUsuario();
            cancha canchaOld = persistentreserva.getCancha();
            cancha canchaNew = reserva.getCancha();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                reserva.setUsuario(usuarioNew);
            }
            if (canchaNew != null) {
                canchaNew = em.getReference(canchaNew.getClass(), canchaNew.getId());
                reserva.setCancha(canchaNew);
            }
            reserva = em.merge(reserva);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getReservas().remove(reserva);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getReservas().add(reserva);
                usuarioNew = em.merge(usuarioNew);
            }
            if (canchaOld != null && !canchaOld.equals(canchaNew)) {
                canchaOld.getReservas().remove(reserva);
                canchaOld = em.merge(canchaOld);
            }
            if (canchaNew != null && !canchaNew.equals(canchaOld)) {
                canchaNew.getReservas().add(reserva);
                canchaNew = em.merge(canchaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = reserva.getId();
                if (findreserva(id) == null) {
                    throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reserva reserva;
            try {
                reserva = em.getReference(reserva.class, id);
                reserva.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.", enfe);
            }
            usuario usuario = reserva.getUsuario();
            if (usuario != null) {
                usuario.getReservas().remove(reserva);
                usuario = em.merge(usuario);
            }
            cancha cancha = reserva.getCancha();
            if (cancha != null) {
                cancha.getReservas().remove(reserva);
                cancha = em.merge(cancha);
            }
            em.remove(reserva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<reserva> findreservaEntities() {
        return findreservaEntities(true, -1, -1);
    }

    public List<reserva> findreservaEntities(int maxResults, int firstResult) {
        return findreservaEntities(false, maxResults, firstResult);
    }

    private List<reserva> findreservaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(reserva.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public reserva findreserva(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(reserva.class, id);
        } finally {
            em.close();
        }
    }

    public int getreservaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<reserva> rt = cq.from(reserva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
