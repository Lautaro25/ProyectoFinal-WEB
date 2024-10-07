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
import logica.cancha;
import logica.horario;
import logica.reserva;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author lauta
 */
public class reservaJpaController implements Serializable {
    
    public reservaJpaController() {
        emf = Persistence.createEntityManagerFactory("PracticaWeb_PU");
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
            cancha cancha = reserva.getCancha();
            if (cancha != null) {
                cancha = em.getReference(cancha.getClass(), cancha.getId());
                reserva.setCancha(cancha);
            }
            horario horario = reserva.getHorario();
            if (horario != null) {
                horario = em.getReference(horario.getClass(), horario.getId());
                reserva.setHorario(horario);
            }
            em.persist(reserva);
            if (cancha != null) {
                cancha.getReservas().add(reserva);
                cancha = em.merge(cancha);
            }
            if (horario != null) {
                horario.getReservas().add(reserva);
                horario = em.merge(horario);
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
            cancha canchaOld = persistentreserva.getCancha();
            cancha canchaNew = reserva.getCancha();
            horario horarioOld = persistentreserva.getHorario();
            horario horarioNew = reserva.getHorario();
            if (canchaNew != null) {
                canchaNew = em.getReference(canchaNew.getClass(), canchaNew.getId());
                reserva.setCancha(canchaNew);
            }
            if (horarioNew != null) {
                horarioNew = em.getReference(horarioNew.getClass(), horarioNew.getId());
                reserva.setHorario(horarioNew);
            }
            reserva = em.merge(reserva);
            if (canchaOld != null && !canchaOld.equals(canchaNew)) {
                canchaOld.getReservas().remove(reserva);
                canchaOld = em.merge(canchaOld);
            }
            if (canchaNew != null && !canchaNew.equals(canchaOld)) {
                canchaNew.getReservas().add(reserva);
                canchaNew = em.merge(canchaNew);
            }
            if (horarioOld != null && !horarioOld.equals(horarioNew)) {
                horarioOld.getReservas().remove(reserva);
                horarioOld = em.merge(horarioOld);
            }
            if (horarioNew != null && !horarioNew.equals(horarioOld)) {
                horarioNew.getReservas().add(reserva);
                horarioNew = em.merge(horarioNew);
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
            cancha cancha = reserva.getCancha();
            if (cancha != null) {
                cancha.getReservas().remove(reserva);
                cancha = em.merge(cancha);
            }
            horario horario = reserva.getHorario();
            if (horario != null) {
                horario.getReservas().remove(reserva);
                horario = em.merge(horario);
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
