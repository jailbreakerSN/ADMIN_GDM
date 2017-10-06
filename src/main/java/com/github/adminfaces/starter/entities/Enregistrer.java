/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.entities;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jailbreaker
 */
@Entity
@Table(name = "enregistrer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enregistrer.findAll", query = "SELECT e FROM Enregistrer e"),
    @NamedQuery(name = "Enregistrer.findByIDService", query = "SELECT e FROM Enregistrer e WHERE e.enregistrerPK.iDService = :iDService"),
    @NamedQuery(name = "Enregistrer.findByIDPatient", query = "SELECT e FROM Enregistrer e WHERE e.enregistrerPK.iDPatient = :iDPatient"),
    @NamedQuery(name = "Enregistrer.findByDateCreation", query = "SELECT e FROM Enregistrer e WHERE e.dateCreation = :dateCreation")})
public class Enregistrer implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EnregistrerPK enregistrerPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateCreation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @JoinColumn(name = "ID_Patient", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Patient patient;
    @JoinColumn(name = "ID_Service", referencedColumnName = "ID_Service", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Service service;

    public Enregistrer() {
    }

    public Enregistrer(EnregistrerPK enregistrerPK) {
        this.enregistrerPK = enregistrerPK;
    }

    public Enregistrer(EnregistrerPK enregistrerPK, Date dateCreation) {
        this.enregistrerPK = enregistrerPK;
        this.dateCreation = dateCreation;
    }

    public Enregistrer(int iDService, int iDPatient) {
        this.enregistrerPK = new EnregistrerPK(iDService, iDPatient);
    }

    public EnregistrerPK getEnregistrerPK() {
        return enregistrerPK;
    }

    public void setEnregistrerPK(EnregistrerPK enregistrerPK) {
        this.enregistrerPK = enregistrerPK;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enregistrerPK != null ? enregistrerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enregistrer)) {
            return false;
        }
        Enregistrer other = (Enregistrer) object;
        if ((this.enregistrerPK == null && other.enregistrerPK != null) || (this.enregistrerPK != null && !this.enregistrerPK.equals(other.enregistrerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.adminfaces.starter.entities.Enregistrer[ enregistrerPK=" + enregistrerPK + " ]";
    }
    
}
