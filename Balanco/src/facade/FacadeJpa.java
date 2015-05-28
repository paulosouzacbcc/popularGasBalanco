/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import model.Cliente;
import model.Venda;

/**
 *
 * @author paulosouza
 */
public class FacadeJpa {

    private static FacadeJpa instance = null;
    private final Venda venda;
    private final Cliente cliente;

    public FacadeJpa() {
        venda = new Venda();
        cliente = new Cliente();
    }

    public static FacadeJpa getInstance() {
        if (instance == null) {
            instance = new FacadeJpa();
        }
        return instance;
    }

    public Venda getVenda() {
        return venda;
    }

    public Cliente getCliente() {
        return cliente;
    }

}
