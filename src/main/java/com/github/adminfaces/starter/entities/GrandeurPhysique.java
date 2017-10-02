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
 * @author JailbreakerSN
 */
@Entity
@Table(name = "grandeur_physique")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrandeurPhysique.findAll", query = "SELECT g FROM GrandeurPhysique g"),
    @NamedQuery(name = "GrandeurPhysique.findById", query = "SELECT g FROM GrandeurPhysique g WHERE g.id = :id"),
    @NamedQuery(name = "GrandeurPhysique.findByNom", query = "SELECT g FROM GrandeurPhysique g WHERE g.nom = :nom"),
    @NamedQuery(name = "GrandeurPhysique.findByDescription", query = "SELECT g FROM GrandeurPhysique g WHERE g.description = :description")})
public class GrandeurPhysique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 45)
    @Column(name = "NOM")
    private String nom;
    @Size(max = 45)
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grandeurPhysique")
    private List<Mesure> mesureList;

    public GrandeurPhysique() {
    }

    public GrandeurPhysique(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Mesure> getMesureList() {
        return mesureList;
    }

    public void setMesureList(List<Mesure> mesureList) {
        this.mesureList = mesureList;
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
        if (!(object instanceof GrandeurPhysique)) {
            return false;
        }
        GrandeurPhysique other = (GrandeurPhysique) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }
    
}
