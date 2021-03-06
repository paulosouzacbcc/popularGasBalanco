/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.FacadeJpa;
import model.Cliente;

/**
 *
 * @author paulosouza
 */
public class ControllerCliente {

    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public boolean criarNovoCliente(Cliente cliente) {

        try {
            facadeJpa.getCliente().create(cliente);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean editarCliente(Cliente cliente){
        try {
            facadeJpa.getCliente().edit(cliente);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean excluir(Cliente cliente)
    {
        try {
            facadeJpa.getCliente().destroy(cliente.getIdcliente());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
