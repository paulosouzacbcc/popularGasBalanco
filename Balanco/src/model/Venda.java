/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paulosouza
 */
@Entity
@Table(name = "venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v"),
    @NamedQuery(name = "Venda.findByIdvenda", query = "SELECT v FROM Venda v WHERE v.vendaPK.idvenda = :idvenda"),
    @NamedQuery(name = "Venda.findByData", query = "SELECT v FROM Venda v WHERE v.data = :data"),
    @NamedQuery(name = "Venda.findByIdcliente", query = "SELECT v FROM Venda v WHERE v.vendaPK.idcliente = :idcliente"),
    @NamedQuery(name = "Venda.findByValor", query = "SELECT v FROM Venda v WHERE v.valor = :valor")})
public class Venda implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VendaPK vendaPK;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;

    public Venda() {
    }

    public Venda(VendaPK vendaPK) {
        this.vendaPK = vendaPK;
    }

    public Venda(VendaPK vendaPK, Date data, double valor) {
        this.vendaPK = vendaPK;
        this.data = data;
        this.valor = valor;
    }

    public Venda(int idvenda, int idcliente) {
        this.vendaPK = new VendaPK(idvenda, idcliente);
    }

    public VendaPK getVendaPK() {
        return vendaPK;
    }

    public void setVendaPK(VendaPK vendaPK) {
        this.vendaPK = vendaPK;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendaPK != null ? vendaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.vendaPK == null && other.vendaPK != null) || (this.vendaPK != null && !this.vendaPK.equals(other.vendaPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Venda[ vendaPK=" + vendaPK + " ]";
    }

}