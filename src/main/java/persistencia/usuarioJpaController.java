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
import logica.torneo;
import logica.tipoUsuario;
import logica.reserva;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import logica.usuario;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author lauta
 */
public class usuarioJpaController implements Serializable {
    
    public usuarioJpaController(){
        emf = Persistence.createEntityManagerFactory("PracticaWebPrueba_PU");
    }

    public usuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(usuario usuario) {
        if (usuario.getReservas() == null) {
            usuario.setReservas(new ArrayList<reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            torneo torneo = usuario.getTorneo();
            if (torneo != null) {
                torneo = em.getReference(torneo.getClass(), torneo.getId());
                usuario.setTorneo(torneo);
            }
            tipoUsuario tipoUsuario = usuario.getTipoUsuario();
            if (tipoUsuario != null) {
                tipoUsuario = em.getReference(tipoUsuario.getClass(), tipoUsuario.getId());
                usuario.setTipoUsuario(tipoUsuario);
            }
            List<reserva> attachedReservas = new ArrayList<reserva>();
            for (reserva reservasreservaToAttach : usuario.getReservas()) {
                reservasreservaToAttach = em.getReference(reservasreservaToAttach.getClass(), reservasreservaToAttach.getId());
                attachedReservas.add(reservasreservaToAttach);
            }
            usuario.setReservas(attachedReservas);
            em.persist(usuario);
            if (torneo != null) {
                usuario oldUsuarioOfTorneo = torneo.getUsuario();
                if (oldUsuarioOfTorneo != null) {
                    oldUsuarioOfTorneo.setTorneo(null);
                    oldUsuarioOfTorneo = em.merge(oldUsuarioOfTorneo);
                }
                torneo.setUsuario(usuario);
                torneo = em.merge(torneo);
            }
            if (tipoUsuario != null) {
                usuario oldUsuarioOfTipoUsuario = tipoUsuario.getUsuario();
                if (oldUsuarioOfTipoUsuario != null) {
                    oldUsuarioOfTipoUsuario.setTipoUsuario(null);
                    oldUsuarioOfTipoUsuario = em.merge(oldUsuarioOfTipoUsuario);
                }
                tipoUsuario.setUsuario(usuario);
                tipoUsuario = em.merge(tipoUsuario);
            }
            for (reserva reservasreserva : usuario.getReservas()) {
                usuario oldUsuarioOfReservasreserva = reservasreserva.getUsuario();
                reservasreserva.setUsuario(usuario);
                reservasreserva = em.merge(reservasreserva);
                if (oldUsuarioOfReservasreserva != null) {
                    oldUsuarioOfReservasreserva.getReservas().remove(reservasreserva);
                    oldUsuarioOfReservasreserva = em.merge(oldUsuarioOfReservasreserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuario persistentusuario = em.find(usuario.class, usuario.getId());
            torneo torneoOld = persistentusuario.getTorneo();
            torneo torneoNew = usuario.getTorneo();
            tipoUsuario tipoUsuarioOld = persistentusuario.getTipoUsuario();
            tipoUsuario tipoUsuarioNew = usuario.getTipoUsuario();
            List<reserva> reservasOld = persistentusuario.getReservas();
            List<reserva> reservasNew = usuario.getReservas();
            if (torneoNew != null) {
                torneoNew = em.getReference(torneoNew.getClass(), torneoNew.getId());
                usuario.setTorneo(torneoNew);
            }
            if (tipoUsuarioNew != null) {
                tipoUsuarioNew = em.getReference(tipoUsuarioNew.getClass(), tipoUsuarioNew.getId());
                usuario.setTipoUsuario(tipoUsuarioNew);
            }
            List<reserva> attachedReservasNew = new ArrayList<reserva>();
            for (reserva reservasNewreservaToAttach : reservasNew) {
                reservasNewreservaToAttach = em.getReference(reservasNewreservaToAttach.getClass(), reservasNewreservaToAttach.getId());
                attachedReservasNew.add(reservasNewreservaToAttach);
            }
            reservasNew = attachedReservasNew;
            usuario.setReservas(reservasNew);
            usuario = em.merge(usuario);
            if (torneoOld != null && !torneoOld.equals(torneoNew)) {
                torneoOld.setUsuario(null);
                torneoOld = em.merge(torneoOld);
            }
            if (torneoNew != null && !torneoNew.equals(torneoOld)) {
                usuario oldUsuarioOfTorneo = torneoNew.getUsuario();
                if (oldUsuarioOfTorneo != null) {
                    oldUsuarioOfTorneo.setTorneo(null);
                    oldUsuarioOfTorneo = em.merge(oldUsuarioOfTorneo);
                }
                torneoNew.setUsuario(usuario);
                torneoNew = em.merge(torneoNew);
            }
            if (tipoUsuarioOld != null && !tipoUsuarioOld.equals(tipoUsuarioNew)) {
                tipoUsuarioOld.setUsuario(null);
                tipoUsuarioOld = em.merge(tipoUsuarioOld);
            }
            if (tipoUsuarioNew != null && !tipoUsuarioNew.equals(tipoUsuarioOld)) {
                usuario oldUsuarioOfTipoUsuario = tipoUsuarioNew.getUsuario();
                if (oldUsuarioOfTipoUsuario != null) {
                    oldUsuarioOfTipoUsuario.setTipoUsuario(null);
                    oldUsuarioOfTipoUsuario = em.merge(oldUsuarioOfTipoUsuario);
                }
                tipoUsuarioNew.setUsuario(usuario);
                tipoUsuarioNew = em.merge(tipoUsuarioNew);
            }
            for (reserva reservasOldreserva : reservasOld) {
                if (!reservasNew.contains(reservasOldreserva)) {
                    reservasOldreserva.setUsuario(null);
                    reservasOldreserva = em.merge(reservasOldreserva);
                }
            }
            for (reserva reservasNewreserva : reservasNew) {
                if (!reservasOld.contains(reservasNewreserva)) {
                    usuario oldUsuarioOfReservasNewreserva = reservasNewreserva.getUsuario();
                    reservasNewreserva.setUsuario(usuario);
                    reservasNewreserva = em.merge(reservasNewreserva);
                    if (oldUsuarioOfReservasNewreserva != null && !oldUsuarioOfReservasNewreserva.equals(usuario)) {
                        oldUsuarioOfReservasNewreserva.getReservas().remove(reservasNewreserva);
                        oldUsuarioOfReservasNewreserva = em.merge(oldUsuarioOfReservasNewreserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuario.getId();
                if (findusuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            usuario usuario;
            try {
                usuario = em.getReference(usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            torneo torneo = usuario.getTorneo();
            if (torneo != null) {
                torneo.setUsuario(null);
                torneo = em.merge(torneo);
            }
            tipoUsuario tipoUsuario = usuario.getTipoUsuario();
            if (tipoUsuario != null) {
                tipoUsuario.setUsuario(null);
                tipoUsuario = em.merge(tipoUsuario);
            }
            List<reserva> reservas = usuario.getReservas();
            for (reserva reservasreserva : reservas) {
                reservasreserva.setUsuario(null);
                reservasreserva = em.merge(reservasreserva);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<usuario> findusuarioEntities() {
        return findusuarioEntities(true, -1, -1);
    }

    public List<usuario> findusuarioEntities(int maxResults, int firstResult) {
        return findusuarioEntities(false, maxResults, firstResult);
    }

    private List<usuario> findusuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(usuario.class));
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

    public usuario findusuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getusuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<usuario> rt = cq.from(usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
