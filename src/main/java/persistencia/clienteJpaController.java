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
import logica.cliente;
import logica.usuario;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author lauta
 */
public class clienteJpaController implements Serializable {
    
    public clienteJpaController() {
        emf = Persistence.createEntityManagerFactory("PracticaWeb_PU");
    }

    public clienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(cliente cliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuario usuario = cliente.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                cliente.setUsuario(usuario);
            }
            em.persist(cliente);
            if (usuario != null) {
                cliente oldClienteOfUsuario = usuario.getCliente();
                if (oldClienteOfUsuario != null) {
                    oldClienteOfUsuario.setUsuario(null);
                    oldClienteOfUsuario = em.merge(oldClienteOfUsuario);
                }
                usuario.setCliente(cliente);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cliente persistentcliente = em.find(cliente.class, cliente.getId());
            usuario usuarioOld = persistentcliente.getUsuario();
            usuario usuarioNew = cliente.getUsuario();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                cliente.setUsuario(usuarioNew);
            }
            cliente = em.merge(cliente);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.setCliente(null);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                cliente oldClienteOfUsuario = usuarioNew.getCliente();
                if (oldClienteOfUsuario != null) {
                    oldClienteOfUsuario.setUsuario(null);
                    oldClienteOfUsuario = em.merge(oldClienteOfUsuario);
                }
                usuarioNew.setCliente(cliente);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cliente.getId();
                if (findcliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            cliente cliente;
            try {
                cliente = em.getReference(cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            usuario usuario = cliente.getUsuario();
            if (usuario != null) {
                usuario.setCliente(null);
                usuario = em.merge(usuario);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<cliente> findclienteEntities() {
        return findclienteEntities(true, -1, -1);
    }

    public List<cliente> findclienteEntities(int maxResults, int firstResult) {
        return findclienteEntities(false, maxResults, firstResult);
    }

    private List<cliente> findclienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(cliente.class));
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

    public cliente findcliente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getclienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<cliente> rt = cq.from(cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
