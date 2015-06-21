/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import com.sun.org.apache.bcel.internal.generic.Select;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.ClienteJpaController;
import model.Cliente;
import org.w3c.dom.Entity;
import util.Conexao;

/**
 *
 * @author paulosouza
 */
public class ClienteJpa extends ClienteJpaController {

    public ClienteJpa() {
        super(Conexao.conectar());
    }
    
    public List<Cliente> findByNome(String nome){
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.nome LIKE :nome ORDER BY c.nome ASC");
            query.setParameter("nome", nome + "%");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Cliente> selectAllCliente(){
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT c FROM Cliente c ORDER BY c.nome ASC");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
