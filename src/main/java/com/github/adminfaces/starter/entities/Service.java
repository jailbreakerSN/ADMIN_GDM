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
@Table(name = "service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),    
    @NamedQuery(name = "Service.findAdmin", query = "SELECT p FROM Service s, Personnel p WHERE s.iDService = :idService AND p.iDTypeEmploye.iDTypeEmploye=3 AND p.iDService.iDService=s.iDService"),
    @NamedQuery(name = "Service.findAllService", query = "SELECT s FROM Service s WHERE s.iDService = :idService"),
    @NamedQuery(name = "Service.findAllStructure", query = "SELECT s FROM Service s WHERE s.iDStructure.iDStructure = :idStructure"),
    @NamedQuery(name = "Service.findByIDService", query = "SELECT s FROM Service s WHERE s.iDService = :iDService"),
    @NamedQuery(name = "Service.findPatients", query = "SELECT pt FROM Service s, Enregistrer e, Patient pt WHERE s.iDService = :iDService AND s.iDService = e.service.iDService AND e.patient.id = pt.id"),
    @NamedQuery(name = "Service.findPatientStructure", query = "SELECT pt FROM Service s, Enregistrer e, Patient pt WHERE s.iDStructure.iDStructure = :idStructure AND s.iDService = e.service.iDService AND e.patient.id = pt.id"),

    @NamedQuery(name = "Service.findmaladies", query = "SELECT m FROM Service s, Maladie m WHERE s.iDService = :iDService AND s.iDService = m.iDService.iDService"),
    @NamedQuery(name = "Service.findmaladieStructure", query = "SELECT m FROM Service s, Maladie m WHERE s.iDStructure.iDStructure = :idStructure AND s.iDService = m.iDService.iDService"),
    @NamedQuery(name = "Service.findByNomServiceService", query = "SELECT s FROM Service s WHERE s.nomServiceService = :nomServiceService"),
    @NamedQuery(name = "Service.findByDescriptionService", query = "SELECT s FROM Service s WHERE s.descriptionService = :descriptionService")})
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Service")
    private Integer iDService;
    @Size(max = 30)
    @Column(name = "nomService_Service")
    private String nomServiceService;
    @Size(max = 255)
    @Column(name = "description_Service")
    private String descriptionService;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "service")
    private List<Enregistrer> enregistrerList;
    @JoinColumn(name = "ID_Structure", referencedColumnName = "ID_Structure")
    @ManyToOne(optional = false)
    private Structure iDStructure;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDService")
    private List<Personnel> personnelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDService")
    private List<Maladie> maladieList;

    public Service() {
    }

    public Service(Integer iDService) {
        this.iDService = iDService;
    }

    public Integer getIDService() {
        return iDService;
    }

    public void setIDService(Integer iDService) {
        this.iDService = iDService;
    }

    public String getNomServiceService() {
        return nomServiceService;
    }

    public void setNomServiceService(String nomServiceService) {
        this.nomServiceService = nomServiceService;
    }

    public String getDescriptionService() {
        return descriptionService;
    }

    public void setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
    }

    @XmlTransient
    public List<Enregistrer> getEnregistrerList() {
        return enregistrerList;
    }

    public void setEnregistrerList(List<Enregistrer> enregistrerList) {
        this.enregistrerList = enregistrerList;
    }

    public Structure getIDStructure() {
        return iDStructure;
    }

    public void setIDStructure(Structure iDStructure) {
        this.iDStructure = iDStructure;
    }

    @XmlTransient
    public List<Personnel> getPersonnelList() {
        return personnelList;
    }

    public void setPersonnelList(List<Personnel> personnelList) {
        this.personnelList = personnelList;
    }

    @XmlTransient
    public List<Maladie> getMaladieList() {
        return maladieList;
    }

    public void setMaladieList(List<Maladie> maladieList) {
        this.maladieList = maladieList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDService != null ? iDService.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.iDService == null && other.iDService != null) || (this.iDService != null && !this.iDService.equals(other.iDService))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomServiceService;
    }
    
}
