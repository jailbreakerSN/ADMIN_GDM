/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.entities;

import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "mesure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesure.findAll", query = "SELECT m FROM Mesure m"),
    @NamedQuery(name = "Mesure.findByConsultationID", query = "SELECT m FROM Mesure m WHERE m.mesurePK.consultationID = :consultationID"),
    @NamedQuery(name = "Mesure.findByGrandeurphysiqueID", query = "SELECT m FROM Mesure m WHERE m.mesurePK.grandeurphysiqueID = :grandeurphysiqueID"),
    @NamedQuery(name = "Mesure.findByValeur", query = "SELECT m FROM Mesure m WHERE m.valeur = :valeur")})
public class Mesure implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MesurePK mesurePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valeur")
    private Double valeur;
    @JoinColumn(name = "consultation_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Consultation consultation;
    @JoinColumn(name = "grandeur_physique_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GrandeurPhysique grandeurPhysique;

    public Mesure() {
    }

    public Mesure(MesurePK mesurePK) {
        this.mesurePK = mesurePK;
    }

    public Mesure(int consultationID, int grandeurphysiqueID) {
        this.mesurePK = new MesurePK(consultationID, grandeurphysiqueID);
    }

    public MesurePK getMesurePK() {
        return mesurePK;
    }

    public void setMesurePK(MesurePK mesurePK) {
        this.mesurePK = mesurePK;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public GrandeurPhysique getGrandeurPhysique() {
        return grandeurPhysique;
    }

    public void setGrandeurPhysique(GrandeurPhysique grandeurPhysique) {
        this.grandeurPhysique = grandeurPhysique;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mesurePK != null ? mesurePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesure)) {
            return false;
        }
        Mesure other = (Mesure) object;
        if ((this.mesurePK == null && other.mesurePK != null) || (this.mesurePK != null && !this.mesurePK.equals(other.mesurePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.adminfaces.starter.entities.Mesure[ mesurePK=" + mesurePK + " ]";
    }
    
}
