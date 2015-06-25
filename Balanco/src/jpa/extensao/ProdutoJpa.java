/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.ProdutoJpaController;
import model.Produto;
import util.Conexao;

/**
 *
 * @author Paulo
 */
public class ProdutoJpa extends ProdutoJpaController{
    public ProdutoJpa(){
        super(Conexao.conectar());
    }
    
    public List<Produto> selectAllProdutos(){
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT p FROM Produto p ORDER BY p.nome ASC");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Produto> findByNome(String produto){
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT p FROM Produto p WHERE p.nome LIKE :produto ORDER BY p.nome ASC");
            query.setParameter("produto", produto + "%");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
