/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JailbreakerSN
 */
@Entity
@Table(name = "consultation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consultation.findAll", query = "SELECT c FROM Consultation c"),
    @NamedQuery(name = "Consultation.findById", query = "SELECT c FROM Consultation c WHERE c.id = :id"),
    @NamedQuery(name = "Consultation.findByDate", query = "SELECT c FROM Consultation c WHERE c.date = :date"),
    @NamedQuery(name = "Consultation.findByConsultationcol", query = "SELECT c FROM Consultation c WHERE c.consultationcol = :consultationcol")})
public class Consultation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 45)
    @Column(name = "consultationcol")
    private String consultationcol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consultation")
    private List<Mesure> mesureList;
    @JoinColumn(name = "patient_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Patient patientID;

    public Consultation() {
    }

    public Consultation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getConsultationcol() {
        return consultationcol;
    }

    public void setConsultationcol(String consultationcol) {
        this.consultationcol = consultationcol;
    }

    @XmlTransient
    public List<Mesure> getMesureList() {
        return mesureList;
    }

    public void setMesureList(List<Mesure> mesureList) {
        this.mesureList = mesureList;
    }

    public Patient getPatientID() {
        return patientID;
    }

    public void setPatientID(Patient patientID) {
        this.patientID = patientID;
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
        if (!(object instanceof Consultation)) {
            return false;
        }
        Consultation other = (Consultation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + id;
    }

}
