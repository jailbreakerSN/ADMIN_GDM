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
import javax.persistence.Lob;
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
@Table(name = "typepersonnel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typepersonnel.findAll", query = "SELECT t FROM Typepersonnel t"),
    @NamedQuery(name = "Typepersonnel.findByIDTypeEmploye", query = "SELECT t FROM Typepersonnel t WHERE t.iDTypeEmploye = :iDTypeEmploye"),
    @NamedQuery(name = "Typepersonnel.findByLibelleTypeTypeEmploye", query = "SELECT t FROM Typepersonnel t WHERE t.libelleTypeTypeEmploye = :libelleTypeTypeEmploye")})
public class Typepersonnel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TypeEmploye")
    private Integer iDTypeEmploye;
    @Size(max = 40)
    @Column(name = "libelleType_TypeEmploye")
    private String libelleTypeTypeEmploye;
    @Lob
    @Size(max = 65535)
    @Column(name = "description_TypeEmploye")
    private String descriptionTypeEmploye;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDTypeEmploye")
    private List<Personnel> personnelList;

    public Typepersonnel() {
    }

    public Typepersonnel(Integer iDTypeEmploye) {
        this.iDTypeEmploye = iDTypeEmploye;
    }

    public Integer getIDTypeEmploye() {
        return iDTypeEmploye;
    }

    public void setIDTypeEmploye(Integer iDTypeEmploye) {
        this.iDTypeEmploye = iDTypeEmploye;
    }

    public String getLibelleTypeTypeEmploye() {
        return libelleTypeTypeEmploye;
    }

    public void setLibelleTypeTypeEmploye(String libelleTypeTypeEmploye) {
        this.libelleTypeTypeEmploye = libelleTypeTypeEmploye;
    }

    public String getDescriptionTypeEmploye() {
        return descriptionTypeEmploye;
    }

    public void setDescriptionTypeEmploye(String descriptionTypeEmploye) {
        this.descriptionTypeEmploye = descriptionTypeEmploye;
    }

    @XmlTransient
    public List<Personnel> getPersonnelList() {
        return personnelList;
    }

    public void setPersonnelList(List<Personnel> personnelList) {
        this.personnelList = personnelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDTypeEmploye != null ? iDTypeEmploye.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typepersonnel)) {
            return false;
        }
        Typepersonnel other = (Typepersonnel) object;
        if ((this.iDTypeEmploye == null && other.iDTypeEmploye != null) || (this.iDTypeEmploye != null && !this.iDTypeEmploye.equals(other.iDTypeEmploye))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getLibelleTypeTypeEmploye();
    }
    
}
