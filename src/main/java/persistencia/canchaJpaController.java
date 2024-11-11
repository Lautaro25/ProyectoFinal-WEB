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
import logica.reserva;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import logica.cancha;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author lauta
 */
public class canchaJpaController implements Serializable {
    
    public canchaJpaController(){
        emf = Persistence.createEntityManagerFactory("PracticaWebPrueba_PU");
    }

    public canchaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(cancha cancha) {
        if (cancha.getReservas() == null) {
            cancha.setReservas(new ArrayList<reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<reserva> attachedReservas = new ArrayList<reserva>();
            for (reserva reservasreservaToAttach : cancha.getReservas()) {
                reservasreservaToAttach = em.getReference(reservasreservaToAttach.getClass(), reservasreservaToAttach.getId());
                attachedReservas.add(reservasreservaToAttach);
            }
            cancha.setReservas(attachedReservas);
            em.persist(cancha);
            for (reserva reservasreserva : cancha.getReservas()) {
                cancha oldCanchaOfReservasreserva = reservasreserva.getCancha();
                reservasreserva.setCancha(cancha);
                reservasreserva = em.merge(reservasreserva);
                if (oldCanchaOfReservasreserva != null) {
                    oldCanchaOfReservasreserva.getReservas().remove(reservasreserva);
                    oldCanchaOfReservasreserva = em.merge(oldCanchaOfReservasreserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(cancha cancha) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cancha persistentcancha = em.find(cancha.class, cancha.getId());
            List<reserva> reservasOld = persistentcancha.getReservas();
            List<reserva> reservasNew = cancha.getReservas();
            List<reserva> attachedReservasNew = new ArrayList<reserva>();
            for (reserva reservasNewreservaToAttach : reservasNew) {
                reservasNewreservaToAttach = em.getReference(reservasNewreservaToAttach.getClass(), reservasNewreservaToAttach.getId());
                attachedReservasNew.add(reservasNewreservaToAttach);
            }
            reservasNew = attachedReservasNew;
            cancha.setReservas(reservasNew);
            cancha = em.merge(cancha);
            for (reserva reservasOldreserva : reservasOld) {
                if (!reservasNew.contains(reservasOldreserva)) {
                    reservasOldreserva.setCancha(null);
                    reservasOldreserva = em.merge(reservasOldreserva);
                }
            }
            for (reserva reservasNewreserva : reservasNew) {
                if (!reservasOld.contains(reservasNewreserva)) {
                    cancha oldCanchaOfReservasNewreserva = reservasNewreserva.getCancha();
                    reservasNewreserva.setCancha(cancha);
                    reservasNewreserva = em.merge(reservasNewreserva);
                    if (oldCanchaOfReservasNewreserva != null && !oldCanchaOfReservasNewreserva.equals(cancha)) {
                        oldCanchaOfReservasNewreserva.getReservas().remove(reservasNewreserva);
                        oldCanchaOfReservasNewreserva = em.merge(oldCanchaOfReservasNewreserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cancha.getId();
                if (findcancha(id) == null) {
                    throw new NonexistentEntityException("The cancha with id " + id + " no longer exists.");
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
            cancha cancha;
            try {
                cancha = em.getReference(cancha.class, id);
                cancha.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cancha with id " + id + " no longer exists.", enfe);
            }
            List<reserva> reservas = cancha.getReservas();
            for (reserva reservasreserva : reservas) {
                reservasreserva.setCancha(null);
                reservasreserva = em.merge(reservasreserva);
            }
            em.remove(cancha);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<cancha> findcanchaEntities() {
        return findcanchaEntities(true, -1, -1);
    }

    public List<cancha> findcanchaEntities(int maxResults, int firstResult) {
        return findcanchaEntities(false, maxResults, firstResult);
    }

    private List<cancha> findcanchaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(cancha.class));
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

    public cancha findcancha(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(cancha.class, id);
        } finally {
            em.close();
        }
    }

    public int getcanchaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<cancha> rt = cq.from(cancha.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
