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
    
    public Map<Service, Long> nombreParService(Personnel P) {
        Map<Service, Long> res = new HashMap<>();
        List<Service> maListe = findAll();
        for (Iterator<Service> iterator = maListe.iterator(); iterator.hasNext();) {
            Service nextService = iterator.next();
            Query query = em.createNamedQuery("PatientHasMaladie.findByService");
            query.setParameter("idService", nextService.getIDService());
            query.setParameter("idStructure", P.getIDService().getIDStructure().getIDStructure());
            Long nombre = (Long) query.getSingleResult();
            res.put(nextService, nombre);
        }
        return res;
    }
}
