/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Cliente;
import model.Vendaproduto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import model.Venda;
import model.VendaPK;

/**
 *
 * @author Paulo
 */
public class VendaJpaController implements Serializable {

    public VendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venda venda) throws PreexistingEntityException, Exception {
        if (venda.getVendaPK() == null) {
            venda.setVendaPK(new VendaPK());
        }
        if (venda.getVendaprodutoList() == null) {
            venda.setVendaprodutoList(new ArrayList<Vendaproduto>());
        }
        venda.getVendaPK().setIdcliente(venda.getCliente().getIdcliente());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = venda.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getIdcliente());
                venda.setCliente(cliente);
            }
            List<Vendaproduto> attachedVendaprodutoList = new ArrayList<Vendaproduto>();
            for (Vendaproduto vendaprodutoListVendaprodutoToAttach : venda.getVendaprodutoList()) {
                vendaprodutoListVendaprodutoToAttach = em.getReference(vendaprodutoListVendaprodutoToAttach.getClass(), vendaprodutoListVendaprodutoToAttach.getId());
                attachedVendaprodutoList.add(vendaprodutoListVendaprodutoToAttach);
            }
            venda.setVendaprodutoList(attachedVendaprodutoList);
            em.persist(venda);
            if (cliente != null) {
                cliente.getVendaList().add(venda);
                cliente = em.merge(cliente);
            }
            for (Vendaproduto vendaprodutoListVendaproduto : venda.getVendaprodutoList()) {
                Venda oldVendaOfVendaprodutoListVendaproduto = vendaprodutoListVendaproduto.getVenda();
                vendaprodutoListVendaproduto.setVenda(venda);
                vendaprodutoListVendaproduto = em.merge(vendaprodutoListVendaproduto);
                if (oldVendaOfVendaprodutoListVendaproduto != null) {
                    oldVendaOfVendaprodutoListVendaproduto.getVendaprodutoList().remove(vendaprodutoListVendaproduto);
                    oldVendaOfVendaprodutoListVendaproduto = em.merge(oldVendaOfVendaprodutoListVendaproduto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVenda(venda.getVendaPK()) != null) {
                throw new PreexistingEntityException("Venda " + venda + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venda venda) throws IllegalOrphanException, NonexistentEntityException, Exception {
        venda.getVendaPK().setIdcliente(venda.getCliente().getIdcliente());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venda persistentVenda = em.find(Venda.class, venda.getVendaPK());
            Cliente clienteOld = persistentVenda.getCliente();
            Cliente clienteNew = venda.getCliente();
            List<Vendaproduto> vendaprodutoListOld = persistentVenda.getVendaprodutoList();
            List<Vendaproduto> vendaprodutoListNew = venda.getVendaprodutoList();
            List<String> illegalOrphanMessages = null;
            for (Vendaproduto vendaprodutoListOldVendaproduto : vendaprodutoListOld) {
                if (!vendaprodutoListNew.contains(vendaprodutoListOldVendaproduto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vendaproduto " + vendaprodutoListOldVendaproduto + " since its venda field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getIdcliente());
                venda.setCliente(clienteNew);
            }
            List<Vendaproduto> attachedVendaprodutoListNew = new ArrayList<Vendaproduto>();
            for (Vendaproduto vendaprodutoListNewVendaprodutoToAttach : vendaprodutoListNew) {
                vendaprodutoListNewVendaprodutoToAttach = em.getReference(vendaprodutoListNewVendaprodutoToAttach.getClass(), vendaprodutoListNewVendaprodutoToAttach.getId());
                attachedVendaprodutoListNew.add(vendaprodutoListNewVendaprodutoToAttach);
            }
            vendaprodutoListNew = attachedVendaprodutoListNew;
            venda.setVendaprodutoList(vendaprodutoListNew);
            venda = em.merge(venda);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getVendaList().remove(venda);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getVendaList().add(venda);
                clienteNew = em.merge(clienteNew);
            }
            for (Vendaproduto vendaprodutoListNewVendaproduto : vendaprodutoListNew) {
                if (!vendaprodutoListOld.contains(vendaprodutoListNewVendaproduto)) {
                    Venda oldVendaOfVendaprodutoListNewVendaproduto = vendaprodutoListNewVendaproduto.getVenda();
                    vendaprodutoListNewVendaproduto.setVenda(venda);
                    vendaprodutoListNewVendaproduto = em.merge(vendaprodutoListNewVendaproduto);
                    if (oldVendaOfVendaprodutoListNewVendaproduto != null && !oldVendaOfVendaprodutoListNewVendaproduto.equals(venda)) {
                        oldVendaOfVendaprodutoListNewVendaproduto.getVendaprodutoList().remove(vendaprodutoListNewVendaproduto);
                        oldVendaOfVendaprodutoListNewVendaproduto = em.merge(oldVendaOfVendaprodutoListNewVendaproduto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                VendaPK id = venda.getVendaPK();
                if (findVenda(id) == null) {
                    throw new NonexistentEntityException("The venda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(VendaPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venda venda;
            try {
                venda = em.getReference(Venda.class, id);
                venda.getVendaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venda with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Vendaproduto> vendaprodutoListOrphanCheck = venda.getVendaprodutoList();
            for (Vendaproduto vendaprodutoListOrphanCheckVendaproduto : vendaprodutoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Venda (" + venda + ") cannot be destroyed since the Vendaproduto " + vendaprodutoListOrphanCheckVendaproduto + " in its vendaprodutoList field has a non-nullable venda field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente cliente = venda.getCliente();
            if (cliente != null) {
                cliente.getVendaList().remove(venda);
                cliente = em.merge(cliente);
            }
            em.remove(venda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venda> findVendaEntities() {
        return findVendaEntities(true, -1, -1);
    }

    public List<Venda> findVendaEntities(int maxResults, int firstResult) {
        return findVendaEntities(false, maxResults, firstResult);
    }

    private List<Venda> findVendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venda.class));
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

    public Venda findVenda(VendaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venda.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venda> rt = cq.from(Venda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
