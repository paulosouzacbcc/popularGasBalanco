/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.FacadeJpa;
import model.Venda;

/**
 *
 * @author paulosouza
 */
public class ControllerVenda {

    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public boolean criarNovaVenda(Venda venda)
    {
        try {
            facadeJpa.getVenda().create(venda);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editarVenda(Venda venda)
    {
        try {
            facadeJpa.getVenda().edit(venda);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
