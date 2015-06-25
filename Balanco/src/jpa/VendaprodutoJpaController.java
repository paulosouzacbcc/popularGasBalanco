/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;
import model.Produto;
import model.Venda;
import model.Vendaproduto;

/**
 *
 * @author Paulo
 */
public class VendaprodutoJpaController implements Serializable {

    public VendaprodutoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vendaproduto vendaproduto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto produtoId = vendaproduto.getProdutoId();
            if (produtoId != null) {
                produtoId = em.getReference(produtoId.getClass(), produtoId.getId());
                vendaproduto.setProdutoId(produtoId);
            }
            Venda venda = vendaproduto.getVenda();
            if (venda != null) {
                venda = em.getReference(venda.getClass(), venda.getVendaPK());
                vendaproduto.setVenda(venda);
            }
            em.persist(vendaproduto);
            if (produtoId != null) {
                produtoId.getVendaprodutoList().add(vendaproduto);
                produtoId = em.merge(produtoId);
            }
            if (venda != null) {
                venda.getVendaprodutoList().add(vendaproduto);
                venda = em.merge(venda);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vendaproduto vendaproduto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vendaproduto persistentVendaproduto = em.find(Vendaproduto.class, vendaproduto.getId());
            Produto produtoIdOld = persistentVendaproduto.getProdutoId();
            Produto produtoIdNew = vendaproduto.getProdutoId();
            Venda vendaOld = persistentVendaproduto.getVenda();
            Venda vendaNew = vendaproduto.getVenda();
            if (produtoIdNew != null) {
                produtoIdNew = em.getReference(produtoIdNew.getClass(), produtoIdNew.getId());
                vendaproduto.setProdutoId(produtoIdNew);
            }
            if (vendaNew != null) {
                vendaNew = em.getReference(vendaNew.getClass(), vendaNew.getVendaPK());
                vendaproduto.setVenda(vendaNew);
            }
            vendaproduto = em.merge(vendaproduto);
            if (produtoIdOld != null && !produtoIdOld.equals(produtoIdNew)) {
                produtoIdOld.getVendaprodutoList().remove(vendaproduto);
                produtoIdOld = em.merge(produtoIdOld);
            }
            if (produtoIdNew != null && !produtoIdNew.equals(produtoIdOld)) {
                produtoIdNew.getVendaprodutoList().add(vendaproduto);
                produtoIdNew = em.merge(produtoIdNew);
            }
            if (vendaOld != null && !vendaOld.equals(vendaNew)) {
                vendaOld.getVendaprodutoList().remove(vendaproduto);
                vendaOld = em.merge(vendaOld);
            }
            if (vendaNew != null && !vendaNew.equals(vendaOld)) {
                vendaNew.getVendaprodutoList().add(vendaproduto);
                vendaNew = em.merge(vendaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vendaproduto.getId();
                if (findVendaproduto(id) == null) {
                    throw new NonexistentEntityException("The vendaproduto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vendaproduto vendaproduto;
            try {
                vendaproduto = em.getReference(Vendaproduto.class, id);
                vendaproduto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vendaproduto with id " + id + " no longer exists.", enfe);
            }
            Produto produtoId = vendaproduto.getProdutoId();
            if (produtoId != null) {
                produtoId.getVendaprodutoList().remove(vendaproduto);
                produtoId = em.merge(produtoId);
            }
            Venda venda = vendaproduto.getVenda();
            if (venda != null) {
                venda.getVendaprodutoList().remove(vendaproduto);
                venda = em.merge(venda);
            }
            em.remove(vendaproduto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vendaproduto> findVendaprodutoEntities() {
        return findVendaprodutoEntities(true, -1, -1);
    }

    public List<Vendaproduto> findVendaprodutoEntities(int maxResults, int firstResult) {
        return findVendaprodutoEntities(false, maxResults, firstResult);
    }

    private List<Vendaproduto> findVendaprodutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vendaproduto.class));
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

    public Vendaproduto findVendaproduto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vendaproduto.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendaprodutoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vendaproduto> rt = cq.from(Vendaproduto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
