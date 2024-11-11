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
import logica.tipoUsuario;
import logica.usuario;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author lauta
 */
public class tipoUsuarioJpaController implements Serializable {
    
    public tipoUsuarioJpaController(){
        emf = Persistence.createEntityManagerFactory("PracticaWebPrueba_PU");
    }

    public tipoUsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(tipoUsuario tipoUsuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuario usuario = tipoUsuario.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                tipoUsuario.setUsuario(usuario);
            }
            em.persist(tipoUsuario);
            if (usuario != null) {
                tipoUsuario oldTipoUsuarioOfUsuario = usuario.getTipoUsuario();
                if (oldTipoUsuarioOfUsuario != null) {
                    oldTipoUsuarioOfUsuario.setUsuario(null);
                    oldTipoUsuarioOfUsuario = em.merge(oldTipoUsuarioOfUsuario);
                }
                usuario.setTipoUsuario(tipoUsuario);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(tipoUsuario tipoUsuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoUsuario persistenttipoUsuario = em.find(tipoUsuario.class, tipoUsuario.getId());
            usuario usuarioOld = persistenttipoUsuario.getUsuario();
            usuario usuarioNew = tipoUsuario.getUsuario();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                tipoUsuario.setUsuario(usuarioNew);
            }
            tipoUsuario = em.merge(tipoUsuario);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.setTipoUsuario(null);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                tipoUsuario oldTipoUsuarioOfUsuario = usuarioNew.getTipoUsuario();
                if (oldTipoUsuarioOfUsuario != null) {
                    oldTipoUsuarioOfUsuario.setUsuario(null);
                    oldTipoUsuarioOfUsuario = em.merge(oldTipoUsuarioOfUsuario);
                }
                usuarioNew.setTipoUsuario(tipoUsuario);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tipoUsuario.getId();
                if (findtipoUsuario(id) == null) {
                    throw new NonexistentEntityException("The tipoUsuario with id " + id + " no longer exists.");
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
            tipoUsuario tipoUsuario;
            try {
                tipoUsuario = em.getReference(tipoUsuario.class, id);
                tipoUsuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoUsuario with id " + id + " no longer exists.", enfe);
            }
            usuario usuario = tipoUsuario.getUsuario();
            if (usuario != null) {
                usuario.setTipoUsuario(null);
                usuario = em.merge(usuario);
            }
            em.remove(tipoUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<tipoUsuario> findtipoUsuarioEntities() {
        return findtipoUsuarioEntities(true, -1, -1);
    }

    public List<tipoUsuario> findtipoUsuarioEntities(int maxResults, int firstResult) {
        return findtipoUsuarioEntities(false, maxResults, firstResult);
    }

    private List<tipoUsuario> findtipoUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(tipoUsuario.class));
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

    public tipoUsuario findtipoUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(tipoUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int gettipoUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<tipoUsuario> rt = cq.from(tipoUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
