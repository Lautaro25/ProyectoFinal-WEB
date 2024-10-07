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
import logica.horario;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author lauta
 */
public class horarioJpaController implements Serializable {
    
    public horarioJpaController() {
        emf = Persistence.createEntityManagerFactory("PracticaWeb_PU");
    }

    public horarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(horario horario) {
        if (horario.getReservas() == null) {
            horario.setReservas(new ArrayList<reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<reserva> attachedReservas = new ArrayList<reserva>();
            for (reserva reservasreservaToAttach : horario.getReservas()) {
                reservasreservaToAttach = em.getReference(reservasreservaToAttach.getClass(), reservasreservaToAttach.getId());
                attachedReservas.add(reservasreservaToAttach);
            }
            horario.setReservas(attachedReservas);
            em.persist(horario);
            for (reserva reservasreserva : horario.getReservas()) {
                horario oldHorarioOfReservasreserva = reservasreserva.getHorario();
                reservasreserva.setHorario(horario);
                reservasreserva = em.merge(reservasreserva);
                if (oldHorarioOfReservasreserva != null) {
                    oldHorarioOfReservasreserva.getReservas().remove(reservasreserva);
                    oldHorarioOfReservasreserva = em.merge(oldHorarioOfReservasreserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(horario horario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            horario persistenthorario = em.find(horario.class, horario.getId());
            List<reserva> reservasOld = persistenthorario.getReservas();
            List<reserva> reservasNew = horario.getReservas();
            List<reserva> attachedReservasNew = new ArrayList<reserva>();
            for (reserva reservasNewreservaToAttach : reservasNew) {
                reservasNewreservaToAttach = em.getReference(reservasNewreservaToAttach.getClass(), reservasNewreservaToAttach.getId());
                attachedReservasNew.add(reservasNewreservaToAttach);
            }
            reservasNew = attachedReservasNew;
            horario.setReservas(reservasNew);
            horario = em.merge(horario);
            for (reserva reservasOldreserva : reservasOld) {
                if (!reservasNew.contains(reservasOldreserva)) {
                    reservasOldreserva.setHorario(null);
                    reservasOldreserva = em.merge(reservasOldreserva);
                }
            }
            for (reserva reservasNewreserva : reservasNew) {
                if (!reservasOld.contains(reservasNewreserva)) {
                    horario oldHorarioOfReservasNewreserva = reservasNewreserva.getHorario();
                    reservasNewreserva.setHorario(horario);
                    reservasNewreserva = em.merge(reservasNewreserva);
                    if (oldHorarioOfReservasNewreserva != null && !oldHorarioOfReservasNewreserva.equals(horario)) {
                        oldHorarioOfReservasNewreserva.getReservas().remove(reservasNewreserva);
                        oldHorarioOfReservasNewreserva = em.merge(oldHorarioOfReservasNewreserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = horario.getId();
                if (findhorario(id) == null) {
                    throw new NonexistentEntityException("The horario with id " + id + " no longer exists.");
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
            horario horario;
            try {
                horario = em.getReference(horario.class, id);
                horario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The horario with id " + id + " no longer exists.", enfe);
            }
            List<reserva> reservas = horario.getReservas();
            for (reserva reservasreserva : reservas) {
                reservasreserva.setHorario(null);
                reservasreserva = em.merge(reservasreserva);
            }
            em.remove(horario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<horario> findhorarioEntities() {
        return findhorarioEntities(true, -1, -1);
    }

    public List<horario> findhorarioEntities(int maxResults, int firstResult) {
        return findhorarioEntities(false, maxResults, firstResult);
    }

    private List<horario> findhorarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(horario.class));
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

    public horario findhorario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(horario.class, id);
        } finally {
            em.close();
        }
    }

    public int gethorarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<horario> rt = cq.from(horario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
