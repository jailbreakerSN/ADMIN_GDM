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
 * @author Jailbreaker
 */
@Entity
@Table(name = "personnel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personnel.findAll", query = "SELECT p FROM Personnel p"),
    @NamedQuery(name = "Personnel.findByIDPersonnel", query = "SELECT p FROM Personnel p WHERE p.iDPersonnel = :iDPersonnel"),
    @NamedQuery(name = "Personnel.findByPrenom", query = "SELECT p FROM Personnel p WHERE p.prenom = :prenom"),
    @NamedQuery(name = "Personnel.findAdmin", query = "SELECT p FROM Personnel p WHERE p.iDTypeEmploye.iDTypeEmploye=5"),
    @NamedQuery(name = "Personnel.findByNom", query = "SELECT p FROM Personnel p WHERE p.nom = :nom"),
    @NamedQuery(name = "Personnel.findByDateNaissPersonnel", query = "SELECT p FROM Personnel p WHERE p.dateNaissPersonnel = :dateNaissPersonnel"),
    @NamedQuery(name = "Personnel.findByNumeroTelPersonnel", query = "SELECT p FROM Personnel p WHERE p.numeroTelPersonnel = :numeroTelPersonnel"),
    @NamedQuery(name = "Personnel.findByAdresseMailPersonnel", query = "SELECT p FROM Personnel p WHERE p.adresseMailPersonnel = :adresseMailPersonnel"),
    @NamedQuery(name = "Personnel.findByLoginPersonnel", query = "SELECT p FROM Personnel p WHERE p.loginPersonnel = :loginPersonnel"),
    @NamedQuery(name = "Personnel.findByPasswordPersonnel", query = "SELECT p FROM Personnel p WHERE p.passwordPersonnel = :passwordPersonnel")})
public class Personnel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Personnel")
    private Integer iDPersonnel;
    @Size(max = 60)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 30)
    @Column(name = "nom")
    private String nom;
    @Column(name = "dateNaiss_Personnel")
    @Temporal(TemporalType.DATE)
    private Date dateNaissPersonnel;
    @Size(max = 60)
    @Column(name = "numeroTel_Personnel")
    private String numeroTelPersonnel;
    @Size(max = 60)
    @Column(name = "adresseMail_Personnel")
    private String adresseMailPersonnel;
    @Size(max = 30)
    @Column(name = "login_Personnel")
    private String loginPersonnel;
    @Size(max = 30)
    @Column(name = "password_Personnel")
    private String passwordPersonnel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDPersonnel")
    private List<Rendezvous> rendezvousList;
    @JoinColumn(name = "ID_TypeEmploye", referencedColumnName = "ID_TypeEmploye")
    @ManyToOne(optional = false)
    private Typepersonnel iDTypeEmploye;
    @JoinColumn(name = "ID_Service", referencedColumnName = "ID_Service")
    @ManyToOne(optional = false)
    private Service iDService;

    public Personnel() {
    }

    public Personnel(Integer iDPersonnel) {
        this.iDPersonnel = iDPersonnel;
    }

    public Integer getIDPersonnel() {
        return iDPersonnel;
    }

    public void setIDPersonnel(Integer iDPersonnel) {
        this.iDPersonnel = iDPersonnel;
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

    public Date getDateNaissPersonnel() {
        return dateNaissPersonnel;
    }

    public void setDateNaissPersonnel(Date dateNaissPersonnel) {
        this.dateNaissPersonnel = dateNaissPersonnel;
    }

    public String getNumeroTelPersonnel() {
        return numeroTelPersonnel;
    }

    public void setNumeroTelPersonnel(String numeroTelPersonnel) {
        this.numeroTelPersonnel = numeroTelPersonnel;
    }

    public String getAdresseMailPersonnel() {
        return adresseMailPersonnel;
    }

    public void setAdresseMailPersonnel(String adresseMailPersonnel) {
        this.adresseMailPersonnel = adresseMailPersonnel;
    }

    public String getLoginPersonnel() {
        return loginPersonnel;
    }

    public void setLoginPersonnel(String loginPersonnel) {
        this.loginPersonnel = loginPersonnel;
    }

    public String getPasswordPersonnel() {
        return passwordPersonnel;
    }

    public void setPasswordPersonnel(String passwordPersonnel) {
        this.passwordPersonnel = passwordPersonnel;
    }

    public boolean isSecretaire() {
        boolean b = false;
        if (iDTypeEmploye.getIDTypeEmploye() == 1) {
            b = true;
        }
        return b;
    }

    public boolean isMedecin() {
        boolean b = false;
        if (iDTypeEmploye.getIDTypeEmploye() == 2) {
            b = true;
        }
        return b;
    }

    public boolean isAdminService() {
        boolean b = false;
        if (iDTypeEmploye.getIDTypeEmploye() == 3) {
            b = true;
        }
        return b;
    }

    public boolean isAdminStructure() {
        boolean b = false;
        if (iDTypeEmploye.getIDTypeEmploye() == 4) {
            b = true;
        }
        return b;
    }

    public boolean isAdmin() {
        boolean b = false;
        if (iDTypeEmploye.getIDTypeEmploye() == 5) {
            b = true;
        }
        return b;
    }

    @XmlTransient
    public List<Rendezvous> getRendezvousList() {
        return rendezvousList;
    }

    public void setRendezvousList(List<Rendezvous> rendezvousList) {
        this.rendezvousList = rendezvousList;
    }

    public Typepersonnel getIDTypeEmploye() {
        return iDTypeEmploye;
    }

    public void setIDTypeEmploye(Typepersonnel iDTypeEmploye) {
        this.iDTypeEmploye = iDTypeEmploye;
    }

    public Service getIDService() {
        return iDService;
    }

    public void setIDService(Service iDService) {
        this.iDService = iDService;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDPersonnel != null ? iDPersonnel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personnel)) {
            return false;
        }
        Personnel other = (Personnel) object;
        if ((this.iDPersonnel == null && other.iDPersonnel != null) || (this.iDPersonnel != null && !this.iDPersonnel.equals(other.iDPersonnel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getPrenom() + " " + getNom();
    }

}
