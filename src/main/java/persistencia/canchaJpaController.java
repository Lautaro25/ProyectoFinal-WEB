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
import logica.cliente;
import logica.horario;
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
    
    public canchaJpaController() {
        emf = Persistence.createEntityManagerFactory("PracticaWeb_PU");
    }
    
    

    public canchaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(cancha cancha) {
        if (cancha.getHorarios() == null) {
            cancha.setHorarios(new ArrayList<horario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cliente cliente = cancha.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getId());
                cancha.setCliente(cliente);
            }
            List<horario> attachedHorarios = new ArrayList<horario>();
            for (horario horarioshorarioToAttach : cancha.getHorarios()) {
                horarioshorarioToAttach = em.getReference(horarioshorarioToAttach.getClass(), horarioshorarioToAttach.getId());
                attachedHorarios.add(horarioshorarioToAttach);
            }
            cancha.setHorarios(attachedHorarios);
            em.persist(cancha);
            if (cliente != null) {
                cancha oldCanchaOfCliente = cliente.getCancha();
                if (oldCanchaOfCliente != null) {
                    oldCanchaOfCliente.setCliente(null);
                    oldCanchaOfCliente = em.merge(oldCanchaOfCliente);
                }
                cliente.setCancha(cancha);
                cliente = em.merge(cliente);
            }
            for (horario horarioshorario : cancha.getHorarios()) {
                cancha oldCanchaOfHorarioshorario = horarioshorario.getCancha();
                horarioshorario.setCancha(cancha);
                horarioshorario = em.merge(horarioshorario);
                if (oldCanchaOfHorarioshorario != null) {
                    oldCanchaOfHorarioshorario.getHorarios().remove(horarioshorario);
                    oldCanchaOfHorarioshorario = em.merge(oldCanchaOfHorarioshorario);
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
            cliente clienteOld = persistentcancha.getCliente();
            cliente clienteNew = cancha.getCliente();
            List<horario> horariosOld = persistentcancha.getHorarios();
            List<horario> horariosNew = cancha.getHorarios();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getId());
                cancha.setCliente(clienteNew);
            }
            List<horario> attachedHorariosNew = new ArrayList<horario>();
            for (horario horariosNewhorarioToAttach : horariosNew) {
                horariosNewhorarioToAttach = em.getReference(horariosNewhorarioToAttach.getClass(), horariosNewhorarioToAttach.getId());
                attachedHorariosNew.add(horariosNewhorarioToAttach);
            }
            horariosNew = attachedHorariosNew;
            cancha.setHorarios(horariosNew);
            cancha = em.merge(cancha);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.setCancha(null);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                cancha oldCanchaOfCliente = clienteNew.getCancha();
                if (oldCanchaOfCliente != null) {
                    oldCanchaOfCliente.setCliente(null);
                    oldCanchaOfCliente = em.merge(oldCanchaOfCliente);
                }
                clienteNew.setCancha(cancha);
                clienteNew = em.merge(clienteNew);
            }
            for (horario horariosOldhorario : horariosOld) {
                if (!horariosNew.contains(horariosOldhorario)) {
                    horariosOldhorario.setCancha(null);
                    horariosOldhorario = em.merge(horariosOldhorario);
                }
            }
            for (horario horariosNewhorario : horariosNew) {
                if (!horariosOld.contains(horariosNewhorario)) {
                    cancha oldCanchaOfHorariosNewhorario = horariosNewhorario.getCancha();
                    horariosNewhorario.setCancha(cancha);
                    horariosNewhorario = em.merge(horariosNewhorario);
                    if (oldCanchaOfHorariosNewhorario != null && !oldCanchaOfHorariosNewhorario.equals(cancha)) {
                        oldCanchaOfHorariosNewhorario.getHorarios().remove(horariosNewhorario);
                        oldCanchaOfHorariosNewhorario = em.merge(oldCanchaOfHorariosNewhorario);
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
            cliente cliente = cancha.getCliente();
            if (cliente != null) {
                cliente.setCancha(null);
                cliente = em.merge(cliente);
            }
            List<horario> horarios = cancha.getHorarios();
            for (horario horarioshorario : horarios) {
                horarioshorario.setCancha(null);
                horarioshorario = em.merge(horarioshorario);
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
