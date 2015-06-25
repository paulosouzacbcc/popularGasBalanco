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
import model.Vendaproduto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Produto;

/**
 *
 * @author Paulo
 */
public class ProdutoJpaController implements Serializable {

    public ProdutoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Produto produto) {
        if (produto.getVendaprodutoList() == null) {
            produto.setVendaprodutoList(new ArrayList<Vendaproduto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Vendaproduto> attachedVendaprodutoList = new ArrayList<Vendaproduto>();
            for (Vendaproduto vendaprodutoListVendaprodutoToAttach : produto.getVendaprodutoList()) {
                vendaprodutoListVendaprodutoToAttach = em.getReference(vendaprodutoListVendaprodutoToAttach.getClass(), vendaprodutoListVendaprodutoToAttach.getId());
                attachedVendaprodutoList.add(vendaprodutoListVendaprodutoToAttach);
            }
            produto.setVendaprodutoList(attachedVendaprodutoList);
            em.persist(produto);
            for (Vendaproduto vendaprodutoListVendaproduto : produto.getVendaprodutoList()) {
                Produto oldProdutoIdOfVendaprodutoListVendaproduto = vendaprodutoListVendaproduto.getProdutoId();
                vendaprodutoListVendaproduto.setProdutoId(produto);
                vendaprodutoListVendaproduto = em.merge(vendaprodutoListVendaproduto);
                if (oldProdutoIdOfVendaprodutoListVendaproduto != null) {
                    oldProdutoIdOfVendaprodutoListVendaproduto.getVendaprodutoList().remove(vendaprodutoListVendaproduto);
                    oldProdutoIdOfVendaprodutoListVendaproduto = em.merge(oldProdutoIdOfVendaprodutoListVendaproduto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Produto produto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto persistentProduto = em.find(Produto.class, produto.getId());
            List<Vendaproduto> vendaprodutoListOld = persistentProduto.getVendaprodutoList();
            List<Vendaproduto> vendaprodutoListNew = produto.getVendaprodutoList();
            List<String> illegalOrphanMessages = null;
            for (Vendaproduto vendaprodutoListOldVendaproduto : vendaprodutoListOld) {
                if (!vendaprodutoListNew.contains(vendaprodutoListOldVendaproduto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vendaproduto " + vendaprodutoListOldVendaproduto + " since its produtoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Vendaproduto> attachedVendaprodutoListNew = new ArrayList<Vendaproduto>();
            for (Vendaproduto vendaprodutoListNewVendaprodutoToAttach : vendaprodutoListNew) {
                vendaprodutoListNewVendaprodutoToAttach = em.getReference(vendaprodutoListNewVendaprodutoToAttach.getClass(), vendaprodutoListNewVendaprodutoToAttach.getId());
                attachedVendaprodutoListNew.add(vendaprodutoListNewVendaprodutoToAttach);
            }
            vendaprodutoListNew = attachedVendaprodutoListNew;
            produto.setVendaprodutoList(vendaprodutoListNew);
            produto = em.merge(produto);
            for (Vendaproduto vendaprodutoListNewVendaproduto : vendaprodutoListNew) {
                if (!vendaprodutoListOld.contains(vendaprodutoListNewVendaproduto)) {
                    Produto oldProdutoIdOfVendaprodutoListNewVendaproduto = vendaprodutoListNewVendaproduto.getProdutoId();
                    vendaprodutoListNewVendaproduto.setProdutoId(produto);
                    vendaprodutoListNewVendaproduto = em.merge(vendaprodutoListNewVendaproduto);
                    if (oldProdutoIdOfVendaprodutoListNewVendaproduto != null && !oldProdutoIdOfVendaprodutoListNewVendaproduto.equals(produto)) {
                        oldProdutoIdOfVendaprodutoListNewVendaproduto.getVendaprodutoList().remove(vendaprodutoListNewVendaproduto);
                        oldProdutoIdOfVendaprodutoListNewVendaproduto = em.merge(oldProdutoIdOfVendaprodutoListNewVendaproduto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = produto.getId();
                if (findProduto(id) == null) {
                    throw new NonexistentEntityException("The produto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto produto;
            try {
                produto = em.getReference(Produto.class, id);
                produto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Vendaproduto> vendaprodutoListOrphanCheck = produto.getVendaprodutoList();
            for (Vendaproduto vendaprodutoListOrphanCheckVendaproduto : vendaprodutoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Produto (" + produto + ") cannot be destroyed since the Vendaproduto " + vendaprodutoListOrphanCheckVendaproduto + " in its vendaprodutoList field has a non-nullable produtoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(produto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Produto> findProdutoEntities() {
        return findProdutoEntities(true, -1, -1);
    }

    public List<Produto> findProdutoEntities(int maxResults, int firstResult) {
        return findProdutoEntities(false, maxResults, firstResult);
    }

    private List<Produto> findProdutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Produto.class));
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

    public Produto findProduto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdutoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Produto> rt = cq.from(Produto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
