/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.VendaJpaController;
import model.Venda;
import util.Conexao;

/**
 *
 * @author paulosouza
 */
public class VendaJpa extends VendaJpaController {

    public VendaJpa() {
        super(Conexao.conectar());
    }

    public List<Venda> selectAllVenda() {
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT v FROM Venda v ORDER BY v.data ASC");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public Venda findByNomeSingle(String nome, int idVenda) {
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT v FROM Venda v WHERE v.nomeCliente LIKE :nome AND v.vendaPK.idvenda =:idVenda");
            query.setParameter("idVenda", idVenda);
            query.setParameter("nome", nome + "%");
            return (Venda) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Venda> findByNomeList(String nome) {
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT v FROM Venda v WHERE v.cliente.nome LIKE :nome ORDER BY v.nomeCliente ASC");
            query.setParameter("nome", nome + "%");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
