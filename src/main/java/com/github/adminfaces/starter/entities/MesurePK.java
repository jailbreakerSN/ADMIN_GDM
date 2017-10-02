/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JailbreakerSN
 */
@Embeddable
public class MesurePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "consultation_ID")
    private int consultationID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "grandeur_physique_ID")
    private int grandeurphysiqueID;

    public MesurePK() {
    }

    public MesurePK(int consultationID, int grandeurphysiqueID) {
        this.consultationID = consultationID;
        this.grandeurphysiqueID = grandeurphysiqueID;
    }

    public int getConsultationID() {
        return consultationID;
    }

    public void setConsultationID(int consultationID) {
        this.consultationID = consultationID;
    }

    public int getGrandeurphysiqueID() {
        return grandeurphysiqueID;
    }

    public void setGrandeurphysiqueID(int grandeurphysiqueID) {
        this.grandeurphysiqueID = grandeurphysiqueID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += consultationID;
        hash += grandeurphysiqueID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MesurePK)) {
            return false;
        }
        MesurePK other = (MesurePK) object;
        if (this.consultationID != other.consultationID) {
            return false;
        }
        if (this.grandeurphysiqueID != other.grandeurphysiqueID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.adminfaces.starter.entities.MesurePK[ consultationID=" + consultationID + ", grandeurphysiqueID=" + grandeurphysiqueID + " ]";
    }
    
}
