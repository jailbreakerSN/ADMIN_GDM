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
 * @author Jailbreaker
 */
@Embeddable
public class EnregistrerPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_Service")
    private int iDService;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_Patient")
    private int iDPatient;

    public EnregistrerPK() {
    }

    public EnregistrerPK(int iDService, int iDPatient) {
        this.iDService = iDService;
        this.iDPatient = iDPatient;
    }

    public int getIDService() {
        return iDService;
    }

    public void setIDService(int iDService) {
        this.iDService = iDService;
    }

    public int getIDPatient() {
        return iDPatient;
    }

    public void setIDPatient(int iDPatient) {
        this.iDPatient = iDPatient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iDService;
        hash += (int) iDPatient;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnregistrerPK)) {
            return false;
        }
        EnregistrerPK other = (EnregistrerPK) object;
        if (this.iDService != other.iDService) {
            return false;
        }
        if (this.iDPatient != other.iDPatient) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.adminfaces.starter.entities.EnregistrerPK[ iDService=" + iDService + ", iDPatient=" + iDPatient + " ]";
    }
    
}
