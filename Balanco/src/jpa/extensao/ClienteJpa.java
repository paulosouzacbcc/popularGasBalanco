/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import jpa.ClienteJpaController;
import util.Conexao;

/**
 *
 * @author paulosouza
 */
public class ClienteJpa extends ClienteJpaController {

    public ClienteJpa() {
        super(Conexao.conectar());
    }

}
