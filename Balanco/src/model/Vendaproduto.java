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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "vendaproduto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendaproduto.findAll", query = "SELECT v FROM Vendaproduto v"),
    @NamedQuery(name = "Vendaproduto.findById", query = "SELECT v FROM Vendaproduto v WHERE v.id = :id"),
    @NamedQuery(name = "Vendaproduto.findByDataVenda", query = "SELECT v FROM Vendaproduto v WHERE v.dataVenda = :dataVenda")})
public class Vendaproduto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dataVenda")
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produto produtoId;
    @JoinColumns({
        @JoinColumn(name = "venda_idvenda", referencedColumnName = "idvenda"),
        @JoinColumn(name = "venda_idcliente", referencedColumnName = "idcliente")})
    @ManyToOne(optional = false)
    private Venda venda;

    public Vendaproduto() {
    }

    public Vendaproduto(Integer id) {
        this.id = id;
    }

    public Vendaproduto(Integer id, Date dataVenda) {
        this.id = id;
        this.dataVenda = dataVenda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Produto getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Produto produtoId) {
        this.produtoId = produtoId;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendaproduto)) {
            return false;
        }
        Vendaproduto other = (Vendaproduto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Vendaproduto[ id=" + id + " ]";
    }

}
