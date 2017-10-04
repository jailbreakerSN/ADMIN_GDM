/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jailbreaker
 */
@Entity
@Table(name = "region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r"),
    @NamedQuery(name = "Region.findByIDRegion", query = "SELECT r FROM Region r WHERE r.iDRegion = :iDRegion"),
    @NamedQuery(name = "Region.findByNomRegion", query = "SELECT r FROM Region r WHERE r.nomRegion = :nomRegion")})
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Region")
    private Integer iDRegion;
    @Size(max = 30)
    @Column(name = "nomRegion")
    private String nomRegion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDRegion")
    private List<Departement> departementList;

    public Region() {
    }

    public Region(Integer iDRegion) {
        this.iDRegion = iDRegion;
    }

    public Integer getIDRegion() {
        return iDRegion;
    }

    public void setIDRegion(Integer iDRegion) {
        this.iDRegion = iDRegion;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    @XmlTransient
    public List<Departement> getDepartementList() {
        return departementList;
    }

    public void setDepartementList(List<Departement> departementList) {
        this.departementList = departementList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDRegion != null ? iDRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Region)) {
            return false;
        }
        Region other = (Region) object;
        if ((this.iDRegion == null && other.iDRegion != null) || (this.iDRegion != null && !this.iDRegion.equals(other.iDRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNomRegion();
    }

}
