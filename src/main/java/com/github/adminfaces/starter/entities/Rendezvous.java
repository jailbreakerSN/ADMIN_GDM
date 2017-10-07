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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JailbreakerSN
 */
@Entity
@Table(name = "rendezvous")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rendezvous.findAll", query = "SELECT r FROM Rendezvous r"),
    @NamedQuery(name = "Rendezvous.findByIdRv", query = "SELECT r FROM Rendezvous r WHERE r.idRv = :idRv"),
    @NamedQuery(name = "Rendezvous.findByTitre", query = "SELECT r FROM Rendezvous r WHERE r.titre = :titre"),
    @NamedQuery(name = "Rendezvous.findByDateDebut", query = "SELECT r FROM Rendezvous r WHERE r.dateDebut = :dateDebut")
    })
public class Rendezvous implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rv")
    private Integer idRv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "titre")
    private String titre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateDebut")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;




    @JoinColumn(name = "ID_Personnel", referencedColumnName = "ID_Personnel")
    @ManyToOne(optional = false)
    private Personnel iDPersonnel;

    @JoinColumn(name = "id_patient", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Patient idPatient;

    public Rendezvous() {
    }

    public Rendezvous(Integer idRv) {
        this.idRv = idRv;
    }

    public Rendezvous(Integer idRv, String titre, Date dateDebut) {
        this.idRv = idRv;
        this.titre = titre;
        this.dateDebut = dateDebut;
       
    }

    public Integer getIdRv() {
        return idRv;
    }

    public void setIdRv(Integer idRv) {
        this.idRv = idRv;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

   


    public Personnel getIDPersonnel() {
        return iDPersonnel;
    }

    public void setIDPersonnel(Personnel iDPersonnel) {
        this.iDPersonnel = iDPersonnel;
    }

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRv != null ? idRv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rendezvous)) {
            return false;
        }
        Rendezvous other = (Rendezvous) object;
        if ((this.idRv == null && other.idRv != null) || (this.idRv != null && !this.idRv.equals(other.idRv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return titre + " " + getIdPatient();
    }

}
