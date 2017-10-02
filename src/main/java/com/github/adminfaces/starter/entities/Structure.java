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
import javax.persistence.Lob;
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
@Table(name = "structure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Structure.findAll", query = "SELECT s FROM Structure s"),
    @NamedQuery(name = "Structure.findByIDStructure", query = "SELECT s FROM Structure s WHERE s.iDStructure = :iDStructure"),
    @NamedQuery(name = "Structure.findByNomStructure", query = "SELECT s FROM Structure s WHERE s.nomStructure = :nomStructure")})
public class Structure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Structure")
    private Integer iDStructure;
    @Size(max = 255)
    @Column(name = "nomStructure")
    private String nomStructure;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "ID_Departement", referencedColumnName = "ID_Departement")
    @ManyToOne(optional = false)
    private Departement iDDepartement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDStructure")
    private List<Service> serviceList;

    public Structure() {
    }

    public Structure(Integer iDStructure) {
        this.iDStructure = iDStructure;
    }

    public Integer getIDStructure() {
        return iDStructure;
    }

    public void setIDStructure(Integer iDStructure) {
        this.iDStructure = iDStructure;
    }

    public String getNomStructure() {
        return nomStructure;
    }

    public void setNomStructure(String nomStructure) {
        this.nomStructure = nomStructure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Departement getIDDepartement() {
        return iDDepartement;
    }

    public void setIDDepartement(Departement iDDepartement) {
        this.iDDepartement = iDDepartement;
    }

    @XmlTransient
    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDStructure != null ? iDStructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Structure)) {
            return false;
        }
        Structure other = (Structure) object;
        if ((this.iDStructure == null && other.iDStructure != null) || (this.iDStructure != null && !this.iDStructure.equals(other.iDStructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNomStructure();
    }
    
}
