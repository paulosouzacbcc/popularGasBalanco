/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Paulo
 */
@Embeddable
public class VendaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idvenda")
    private int idvenda;
    @Basic(optional = false)
    @Column(name = "idcliente")
    private int idcliente;

    public VendaPK() {
    }

    public VendaPK(int idvenda, int idcliente) {
        this.idvenda = idvenda;
        this.idcliente = idcliente;
    }

    public int getIdvenda() {
        return idvenda;
    }

    public void setIdvenda(int idvenda) {
        this.idvenda = idvenda;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idvenda;
        hash += (int) idcliente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendaPK)) {
            return false;
        }
        VendaPK other = (VendaPK) object;
        if (this.idvenda != other.idvenda) {
            return false;
        }
        if (this.idcliente != other.idcliente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.VendaPK[ idvenda=" + idvenda + ", idcliente=" + idcliente + " ]";
    }
    
}
