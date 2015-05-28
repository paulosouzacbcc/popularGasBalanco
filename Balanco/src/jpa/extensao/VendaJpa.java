/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import jpa.VendaJpaController;
import util.Conexao;

/**
 *
 * @author paulosouza
 */
public class VendaJpa extends VendaJpaController {

    public VendaJpa() {
        super(Conexao.conectar());
    }
}
