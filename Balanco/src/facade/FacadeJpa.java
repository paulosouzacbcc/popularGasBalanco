/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import jpa.extensao.ClienteJpa;
import jpa.extensao.ProdutoJpa;
import jpa.extensao.VendaJpa;
import model.Produto;

/**
 *
 * @author paulosouza
 */
public class FacadeJpa {

    private static FacadeJpa instance = null;

    private final ClienteJpa clienteJpa;
    private final VendaJpa vendaJpa;
    private final ProdutoJpa produtoJpa;

    public FacadeJpa() {
        clienteJpa = new ClienteJpa();
        vendaJpa = new VendaJpa();
        produtoJpa = new ProdutoJpa();

    }

    public static FacadeJpa getInstance() {
        if (instance == null) {
            instance = new FacadeJpa();
        }
        return instance;
    }

    public VendaJpa getVenda() {
        return vendaJpa;
    }

    public ClienteJpa getCliente() {
        return clienteJpa;
    }
    
    public ProdutoJpa getProduto(){
        return produtoJpa;
    }

}
