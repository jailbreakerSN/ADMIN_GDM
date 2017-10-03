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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JailbreakerSN
 */
@Entity
@Table(name = "patient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p"),
    @NamedQuery(name = "Patient.findById", query = "SELECT p FROM Patient p WHERE p.id = :id"),
    @NamedQuery(name = "Patient.findByPrenom", query = "SELECT p FROM Patient p WHERE p.prenom = :prenom"),
    @NamedQuery(name = "Patient.findByNom", query = "SELECT p FROM Patient p WHERE p.nom = :nom"),
    @NamedQuery(name = "Patient.findByDateNaiss", query = "SELECT p FROM Patient p WHERE p.dateNaiss = :dateNaiss"),
    
    @NamedQuery(name = "Patient.findByLogin", query = "SELECT p FROM Patient p WHERE p.login = :login"),
    @NamedQuery(name = "Patient.findByPassword", query = "SELECT p FROM Patient p WHERE p.password = :password"),
    // Notre requete Globale
    @NamedQuery(name = "Patient.countBySexe", query = "SELECT count(p) FROM Patient p, PatientHasMaladie phm WHERE p.id=phm.patient.id AND p.codeSexe.idSexe = :id"),
    // Notre requete Pour Service
    @NamedQuery(name = "Patient.countBySexeService", query = "SELECT count(p) FROM Patient p, PatientHasMaladie phm WHERE p.id=phm.patient.id AND p.codeSexe.idSexe = :id AND phm.maladie.iDService.iDService = :idService"),
    // Notre requete Pour STructure
    @NamedQuery(name = "Patient.countBySexeStructure", query = "SELECT count(p) FROM Patient p WHERE p.codeSexe.idSexe = :id"),
    @NamedQuery(name = "Patient.findByNumeroTel", query = "SELECT p FROM Patient p WHERE p.numeroTel = :numeroTel")})
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PRENOM")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOM")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(max = 255)
    @Column(name = "login")
    private String login;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Column(name = "DATE_NAISS")
    @Temporal(TemporalType.DATE)
    private Date dateNaiss;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "numeroTel")
    private String numeroTel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<PatientHasMaladie> patientHasMaladieList;
    @JoinColumn(name = "codeSexe", referencedColumnName = "id_sexe")
    @ManyToOne(optional = false)
    private Sexe codeSexe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPatient")
    private List<Rendezvous> rendezvousList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientID")
    private List<Consultation> consultationList;

    public Patient() {
    }

    public Patient(Integer id) {
        this.id = id;
    }

    public Patient(Integer id, String prenom, String nom,String login,String password,Date dateNaiss, String numeroTel) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaiss = dateNaiss;
        this.numeroTel = numeroTel;
        this.login = login;
        this.password= password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
        public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    @XmlTransient
    public List<PatientHasMaladie> getPatientHasMaladieList() {
        return patientHasMaladieList;
    }

    public void setPatientHasMaladieList(List<PatientHasMaladie> patientHasMaladieList) {
        this.patientHasMaladieList = patientHasMaladieList;
    }

    public Sexe getCodeSexe() {
        return codeSexe;
    }

    public void setCodeSexe(Sexe codeSexe) {
        this.codeSexe = codeSexe;
    }

    @XmlTransient
    public List<Rendezvous> getRendezvousList() {
        return rendezvousList;
    }

    public void setRendezvousList(List<Rendezvous> rendezvousList) {
        this.rendezvousList = rendezvousList;
    }

    @XmlTransient
    public List<Consultation> getConsultationList() {
        return consultationList;
    }

    public void setConsultationList(List<Consultation> consultationList) {
        this.consultationList = consultationList;
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
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return prenom+" "+ nom;
    }
    
}
