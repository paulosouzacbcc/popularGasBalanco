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

    public void criarNovoCliente(Cliente cliente) {

        try {
            facadeJpa.getCliente().create(cliente);
            System.out.println("Salvo!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
