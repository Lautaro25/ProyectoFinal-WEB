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
import logica.torneo;
import logica.usuario;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author lauta
 */
public class torneoJpaController implements Serializable {
    
    public torneoJpaController(){
        emf = Persistence.createEntityManagerFactory("PracticaWeb_PU");
    }

    public torneoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(torneo torneo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuario usuario = torneo.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                torneo.setUsuario(usuario);
            }
            em.persist(torneo);
            if (usuario != null) {
                torneo oldTorneoOfUsuario = usuario.getTorneo();
                if (oldTorneoOfUsuario != null) {
                    oldTorneoOfUsuario.setUsuario(null);
                    oldTorneoOfUsuario = em.merge(oldTorneoOfUsuario);
                }
                usuario.setTorneo(torneo);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(torneo torneo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            torneo persistenttorneo = em.find(torneo.class, torneo.getId());
            usuario usuarioOld = persistenttorneo.getUsuario();
            usuario usuarioNew = torneo.getUsuario();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                torneo.setUsuario(usuarioNew);
            }
            torneo = em.merge(torneo);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.setTorneo(null);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                torneo oldTorneoOfUsuario = usuarioNew.getTorneo();
                if (oldTorneoOfUsuario != null) {
                    oldTorneoOfUsuario.setUsuario(null);
                    oldTorneoOfUsuario = em.merge(oldTorneoOfUsuario);
                }
                usuarioNew.setTorneo(torneo);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = torneo.getId();
                if (findtorneo(id) == null) {
                    throw new NonexistentEntityException("The torneo with id " + id + " no longer exists.");
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
            torneo torneo;
            try {
                torneo = em.getReference(torneo.class, id);
                torneo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The torneo with id " + id + " no longer exists.", enfe);
            }
            usuario usuario = torneo.getUsuario();
            if (usuario != null) {
                usuario.setTorneo(null);
                usuario = em.merge(usuario);
            }
            em.remove(torneo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<torneo> findtorneoEntities() {
        return findtorneoEntities(true, -1, -1);
    }

    public List<torneo> findtorneoEntities(int maxResults, int firstResult) {
        return findtorneoEntities(false, maxResults, firstResult);
    }

    private List<torneo> findtorneoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(torneo.class));
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

    public torneo findtorneo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(torneo.class, id);
        } finally {
            em.close();
        }
    }

    public int gettorneoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<torneo> rt = cq.from(torneo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
