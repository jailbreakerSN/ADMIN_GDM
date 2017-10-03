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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JailbreakerSN
 */
@Entity
@Table(name = "maladie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maladie.findAll", query = "SELECT m FROM Maladie m"),
    @NamedQuery(name = "Maladie.findAllService", query = "SELECT m FROM Maladie m WHERE m.iDService.iDService = :idService"),
    @NamedQuery(name = "Maladie.findAllStructure", query = "SELECT m FROM Maladie m WHERE m.iDService.iDStructure.iDStructure = :idStructure"),
    @NamedQuery(name = "Maladie.findById", query = "SELECT m FROM Maladie m WHERE m.id = :id"),
    @NamedQuery(name = "Maladie.findByNom", query = "SELECT m FROM Maladie m WHERE m = :nom"),
    // Notre requete
    @NamedQuery(name = "Maladie.countByMaladie", query = "SELECT count(p) FROM PatientHasMaladie p WHERE p.maladie.id = :id"),
    // Notre requete
    @NamedQuery(name = "Maladie.countByMaladieService", query = "SELECT count(p) FROM PatientHasMaladie p WHERE p.maladie.id = :id AND p.maladie.iDService.iDService = :idService"),
    @NamedQuery(name = "Maladie.countByMaladieByService", query = "SELECT count(p) FROM PatientHasMaladie p WHERE p.maladie.iDService.iDService = :iDService"),
    
    //
    
    @NamedQuery(name = "Maladie.countByMaladieByservice", query = "SELECT m FROM Maladie m WHERE m.iDService = :iDService"),
    
    // Notre requete 2
    @NamedQuery(name = "Maladie.countByMaladieBySexe", query = "SELECT count(p) FROM PatientHasMaladie p WHERE p.maladie.id = :id AND p.patient.codeSexe.idSexe = :idSexe"),
    @NamedQuery(name = "Maladie.findByDescription", query = "SELECT m FROM Maladie m WHERE m.description = :description")})
public class Maladie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOM")
    private String nom;
    @Size(max = 45)
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maladie")
    private List<PatientHasMaladie> patientHasMaladieList;
    
    @JoinColumn(name = "ID_Service", referencedColumnName = "ID_Service")
    @ManyToOne(optional = false)
    private Service iDService;

    public Maladie() {
    }

    public Maladie(Integer id) {
        this.id = id;
    }

    public Maladie(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
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
    public List<PatientHasMaladie> getPatientHasMaladieList() {
        return patientHasMaladieList;
    }

    public void setPatientHasMaladieList(List<PatientHasMaladie> patientHasMaladieList) {
        this.patientHasMaladieList = patientHasMaladieList;
    }
    
    public Service getIDService() {
        return iDService;
    }

    public void setIDService(Service iDService) {
        this.iDService = iDService;
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
        if (!(object instanceof Maladie)) {
            return false;
        }
        Maladie other = (Maladie) object;
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
