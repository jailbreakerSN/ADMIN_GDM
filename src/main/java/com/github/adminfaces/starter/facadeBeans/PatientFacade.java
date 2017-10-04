/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.facadeBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.github.adminfaces.starter.entities.Patient;
import com.github.adminfaces.starter.entities.Personnel;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author JailbreakerSN
 */
@Stateless
public class PatientFacade extends AbstractFacade<Patient> {

    @PersistenceContext(unitName = "com.github.adminfaces_admin-starter_war_0.1-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PatientFacade() {
        super(Patient.class);
    }

    public List<Patient> findRange(Personnel P, int[] range) {
        if (P.isAdminService() || P.isSecretaire() || P.isMedecin()) {
            Query query = em.createNamedQuery("Service.findPatients");
            query.setParameter("iDService", P.getIDService().getIDService());
            return query.getResultList();
        } else if (P.isAdminStructure()) {
            Query query = em.createNamedQuery("Service.findPatientStructure");
            query.setParameter("idStructure", P.getIDService().getIDStructure().getIDStructure());
            return query.getResultList();
        } else {
            Query query = em.createNamedQuery("Patient.findAll");
            return query.getResultList();
        }

    }

}
