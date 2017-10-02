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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "departement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departement.findAll", query = "SELECT d FROM Departement d"),
    @NamedQuery(name = "Departement.findByIDDepartement", query = "SELECT d FROM Departement d WHERE d.iDDepartement = :iDDepartement"),
    @NamedQuery(name = "Departement.findByNomDep", query = "SELECT d FROM Departement d WHERE d.nomDep = :nomDep")})
public class Departement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Departement")
    private Integer iDDepartement;
    @Size(max = 30)
    @Column(name = "nomDep")
    private String nomDep;
    @JoinColumn(name = "ID_Region", referencedColumnName = "ID_Region")
    @ManyToOne(optional = false)
    private Region iDRegion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDDepartement")
    private List<Structure> structureList;

    public Departement() {
    }

    public Departement(Integer iDDepartement) {
        this.iDDepartement = iDDepartement;
    }

    public Integer getIDDepartement() {
        return iDDepartement;
    }

    public void setIDDepartement(Integer iDDepartement) {
        this.iDDepartement = iDDepartement;
    }

    public String getNomDep() {
        return nomDep;
    }

    public void setNomDep(String nomDep) {
        this.nomDep = nomDep;
    }

    public Region getIDRegion() {
        return iDRegion;
    }

    public void setIDRegion(Region iDRegion) {
        this.iDRegion = iDRegion;
    }

    @XmlTransient
    public List<Structure> getStructureList() {
        return structureList;
    }

    public void setStructureList(List<Structure> structureList) {
        this.structureList = structureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDDepartement != null ? iDDepartement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departement)) {
            return false;
        }
        Departement other = (Departement) object;
        if ((this.iDDepartement == null && other.iDDepartement != null) || (this.iDDepartement != null && !this.iDDepartement.equals(other.iDDepartement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNomDep();
    }
    
}
