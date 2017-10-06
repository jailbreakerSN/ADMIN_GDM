/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.facadeBeans;

import com.github.adminfaces.starter.entities.Personnel;
import com.github.adminfaces.starter.entities.Service;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jailbreaker
 */
@Stateless
public class ServiceFacade extends AbstractFacade<Service> {

    @PersistenceContext(unitName = "com.github.adminfaces_admin-starter_war_0.1-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceFacade() {
        super(Service.class);
    }

    public List<Service> findAll(Personnel P) {
        if (P.isAdminStructure()) {
            Query query = em.createNamedQuery("Service.findAllStructure");
            query.setParameter("idStructure", P.getIDService().getIDStructure().getIDStructure());
            return query.getResultList();
        } else if (P.isMedecin() || P.isSecretaire() || P.isAdminService()) {
            Query query = em.createNamedQuery("Service.findAllService");
            query.setParameter("idService", P.getIDService().getIDService());
            return query.getResultList();
        } else {
            Query query = em.createNamedQuery("Service.findAll");
            return query.getResultList();
        }
    }

    public Personnel getAdmin(Service s) {
        Query query = em.createNamedQuery("Service.findAdmin");
        query.setParameter("idService", s.getIDService());
        return (Personnel) query.getSingleResult();
    }

    public Map<Service, Long> nombreParService(Personnel P) {
        Map<Service, Long> res = new HashMap<>();
        List<Service> maListe = findAll(P);
        for (Iterator<Service> iterator = maListe.iterator(); iterator.hasNext();) {
            Service nextService = iterator.next();
            if (P.isAdminStructure()) {
                Query query = em.createNamedQuery("PatientHasMaladie.findByServiceStructure");
                query.setParameter("idService", nextService.getIDService());
                query.setParameter("idStructure", P.getIDService().getIDStructure().getIDStructure());
                Long nombre = (Long) query.getSingleResult();
                res.put(nextService, nombre);
            } else if (P.isAdmin()) {
                Query query = em.createNamedQuery("PatientHasMaladie.findByService");
                query.setParameter("idService", nextService.getIDService());
                Long nombre = (Long) query.getSingleResult();
                res.put(nextService, nombre);
            }
        }
        return res;
    }

    public Map<Service, Long> nombreServStat(Personnel P) {
        Map<Service, Long> res = new HashMap<>();
        List<Service> maListe = findAll(P);
        for (Iterator<Service> iterator = maListe.iterator(); iterator.hasNext();) {
            Service nextService = iterator.next();
            if (P.isAdminStructure()) {
                Query query = em.createNamedQuery("PatientHasMaladie.findByServiceStructure");
                query.setParameter("idService", nextService.getIDService());
                query.setParameter("idStructure", P.getIDService().getIDStructure().getIDStructure());
                Long nombre = (Long) query.getSingleResult();
                res.put(nextService, nombre);
            }
        }
        return res;
    }
}
