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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author FALL
 */
@Entity
@Table(name = "reminder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reminder.findAll", query = "SELECT r FROM Reminder r"),
    @NamedQuery(name = "Reminder.findByIdremind", query = "SELECT r FROM Reminder r WHERE r.idremind = :idremind"),
    @NamedQuery(name = "Reminder.findByPrenompers", query = "SELECT r FROM Reminder r WHERE r.prenompers = :prenompers"),
    @NamedQuery(name = "Reminder.findByNompers", query = "SELECT r FROM Reminder r WHERE r.nompers = :nompers"),
    @NamedQuery(name = "Reminder.findByTelephone", query = "SELECT r FROM Reminder r WHERE r.telephone = :telephone"),
    @NamedQuery(name = "Reminder.findByNommedic", query = "SELECT r FROM Reminder r WHERE r.nommedic = :nommedic"),
    @NamedQuery(name = "Reminder.findByNombre", query = "SELECT r FROM Reminder r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Reminder.findByHeureRap1", query = "SELECT r FROM Reminder r WHERE r.heureRap1 = :heureRap1"),
    @NamedQuery(name = "Reminder.findByHeureRap2", query = "SELECT r FROM Reminder r WHERE r.heureRap2 = :heureRap2"),
    @NamedQuery(name = "Reminder.findByHeureRap3", query = "SELECT r FROM Reminder r WHERE r.heureRap3 = :heureRap3"),
    @NamedQuery(name = "Reminder.findByDateArret", query = "SELECT r FROM Reminder r WHERE r.dateArret = :dateArret")})
public class Reminder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idremind")
    private Integer idremind;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Prenompers")
    private String prenompers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Nompers")
    private String nompers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Telephone")
    private String telephone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Nommedic")
    private String nommedic;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nombre")
    private int nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "heure_rap1")
    @Temporal(TemporalType.TIME)
    private Date heureRap1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "heure_rap2")
    @Temporal(TemporalType.TIME)
    private Date heureRap2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "heure_rap3")
    @Temporal(TemporalType.TIME)
    private Date heureRap3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_arret")
    @Temporal(TemporalType.DATE)
    private Date dateArret;

    public Reminder() {
    }

    public Reminder(Integer idremind) {
        this.idremind = idremind;
    }

    public Reminder(Integer idremind, String prenompers, String nompers, String telephone, String nommedic, int nombre, Date heureRap1, Date heureRap2, Date heureRap3, Date dateArret) {
        this.idremind = idremind;
        this.prenompers = prenompers;
        this.nompers = nompers;
        this.telephone = telephone;
        this.nommedic = nommedic;
        this.nombre = nombre;
        this.heureRap1 = heureRap1;
        this.heureRap2 = heureRap2;
        this.heureRap3 = heureRap3;
        this.dateArret = dateArret;
    }

    public Integer getIdremind() {
        return idremind;
    }

    public void setIdremind(Integer idremind) {
        this.idremind = idremind;
    }

    public String getPrenompers() {
        return prenompers;
    }

    public void setPrenompers(String prenompers) {
        this.prenompers = prenompers;
    }

    public String getNompers() {
        return nompers;
    }

    public void setNompers(String nompers) {
        this.nompers = nompers;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNommedic() {
        return nommedic;
    }

    public void setNommedic(String nommedic) {
        this.nommedic = nommedic;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public Date getHeureRap1() {
        return heureRap1;
    }

    public void setHeureRap1(Date heureRap1) {
        this.heureRap1 = heureRap1;
    }

    public Date getHeureRap2() {
        return heureRap2;
    }

    public void setHeureRap2(Date heureRap2) {
        this.heureRap2 = heureRap2;
    }

    public Date getHeureRap3() {
        return heureRap3;
    }

    public void setHeureRap3(Date heureRap3) {
        this.heureRap3 = heureRap3;
    }

    public Date getDateArret() {
        return dateArret;
    }

    public void setDateArret(Date dateArret) {
        this.dateArret = dateArret;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idremind != null ? idremind.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reminder)) {
            return false;
        }
        Reminder other = (Reminder) object;
        if ((this.idremind == null && other.idremind != null) || (this.idremind != null && !this.idremind.equals(other.idremind))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.adminfaces.starter.entities.Reminder[ idremind=" + idremind + " ]";
    }
    
}
