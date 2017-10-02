/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JailbreakerSN
 */
@Entity
@Table(name = "patient_has_maladie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatientHasMaladie.findAll", query = "SELECT p FROM PatientHasMaladie p"),
    @NamedQuery(name = "PatientHasMaladie.findByPatientID", query = "SELECT p FROM PatientHasMaladie p WHERE p.patientHasMaladiePK.patientID = :patientID"),
    @NamedQuery(name = "PatientHasMaladie.findByMaladieID", query = "SELECT p FROM PatientHasMaladie p WHERE p.patientHasMaladiePK.maladieID = :maladieID"),
    @NamedQuery(name = "PatientHasMaladie.findByDateDiagnostic", query = "SELECT p FROM PatientHasMaladie p WHERE p.patientHasMaladiePK.dateDiagnostic = :dateDiagnostic"),
    // Ma requett
    @NamedQuery(name = "PatientHasMaladie.findByStructure", query = "SELECT count(p) FROM PatientHasMaladie p WHERE p.maladie.iDService.iDStructure.iDStructure = :iDStructure"),
    @NamedQuery(name = "PatientHasMaladie.findBydepartement", query = "SELECT count(p) FROM PatientHasMaladie p WHERE p.maladie.iDService.iDStructure.iDDepartement.iDDepartement = :idDepartement"),
    @NamedQuery(name = "PatientHasMaladie.findByRegion", query = "SELECT count(p) FROM PatientHasMaladie p WHERE p.maladie.iDService.iDStructure.iDDepartement.iDRegion.iDRegion = :idRegion"),
    @NamedQuery(name = "PatientHasMaladie.findByService", query = "SELECT count(p) FROM PatientHasMaladie p WHERE p.maladie.iDService.iDService = :idService")
})
public class PatientHasMaladie implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PatientHasMaladiePK patientHasMaladiePK;
    @JoinColumn(name = "maladie_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Maladie maladie;
    @JoinColumn(name = "patient_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Patient patient;

    public PatientHasMaladie() {
    }

    public PatientHasMaladie(PatientHasMaladiePK patientHasMaladiePK) {
        this.patientHasMaladiePK = patientHasMaladiePK;
    }

    public PatientHasMaladie(int patientID, int maladieID, Date dateDiagnostic) {
        this.patientHasMaladiePK = new PatientHasMaladiePK(patientID, maladieID, dateDiagnostic);
    }

    public PatientHasMaladiePK getPatientHasMaladiePK() {
        return patientHasMaladiePK;
    }

    public void setPatientHasMaladiePK(PatientHasMaladiePK patientHasMaladiePK) {
        this.patientHasMaladiePK = patientHasMaladiePK;
    }

    public Maladie getMaladie() {
        return maladie;
    }

    public void setMaladie(Maladie maladie) {
        this.maladie = maladie;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patientHasMaladiePK != null ? patientHasMaladiePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientHasMaladie)) {
            return false;
        }
        PatientHasMaladie other = (PatientHasMaladie) object;
        if ((this.patientHasMaladiePK == null && other.patientHasMaladiePK != null) || (this.patientHasMaladiePK != null && !this.patientHasMaladiePK.equals(other.patientHasMaladiePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.adminfaces.starter.entities.PatientHasMaladie[ patientHasMaladiePK=" + patientHasMaladiePK + " ]";
    }
    
}
