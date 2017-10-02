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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JailbreakerSN
 */
@Embeddable
public class PatientHasMaladiePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "patient_ID")
    private int patientID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "maladie_ID")
    private int maladieID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_diagnostic")
    @Temporal(TemporalType.DATE)
    private Date dateDiagnostic;

    public PatientHasMaladiePK() {
    }

    public PatientHasMaladiePK(int patientID, int maladieID, Date dateDiagnostic) {
        this.patientID = patientID;
        this.maladieID = maladieID;
        this.dateDiagnostic = dateDiagnostic;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getMaladieID() {
        return maladieID;
    }

    public void setMaladieID(int maladieID) {
        this.maladieID = maladieID;
    }

    public Date getDateDiagnostic() {
        return dateDiagnostic;
    }

    public void setDateDiagnostic(Date dateDiagnostic) {
        this.dateDiagnostic = dateDiagnostic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += patientID;
        hash += maladieID;
        hash += (dateDiagnostic != null ? dateDiagnostic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientHasMaladiePK)) {
            return false;
        }
        PatientHasMaladiePK other = (PatientHasMaladiePK) object;
        if (this.patientID != other.patientID) {
            return false;
        }
        if (this.maladieID != other.maladieID) {
            return false;
        }
        if ((this.dateDiagnostic == null && other.dateDiagnostic != null) || (this.dateDiagnostic != null && !this.dateDiagnostic.equals(other.dateDiagnostic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.adminfaces.starter.entities.PatientHasMaladiePK[ patientID=" + patientID + ", maladieID=" + maladieID + ", dateDiagnostic=" + dateDiagnostic + " ]";
    }
    
}
