/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.FacadeJpa;
import javax.persistence.EntityManager;
import model.Produto;

/**
 *
 * @author Paulo
 */
public class ControllerProduto {
    
    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();
    
    public boolean criarNovoProduto(Produto produto){
        
        try {
            facadeJpa.getProduto().create(produto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean editarProduto(Produto produto){
        try {
            facadeJpa.getProduto().edit(produto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
