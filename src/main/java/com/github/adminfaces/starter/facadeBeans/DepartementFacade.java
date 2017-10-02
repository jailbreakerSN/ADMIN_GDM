/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.facadeBeans;

import com.github.adminfaces.starter.entities.Departement;
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
public class DepartementFacade extends AbstractFacade<Departement> {

    @PersistenceContext(unitName = "com.github.adminfaces_admin-starter_war_0.1-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartementFacade() {
        super(Departement.class);
    }
    
    public Map<Departement, Long> nombreParDepartement() {
        Map<Departement, Long> res = new HashMap<>();
        List<Departement> maListe = findAll();
        for (Iterator<Departement> iterator = maListe.iterator(); iterator.hasNext();) {
            Departement nextDepartement = iterator.next();
            Query query = em.createNamedQuery("PatientHasMaladie.findBydepartement");
            query.setParameter("idDepartement", nextDepartement.getIDDepartement());
            Long nombre = (Long) query.getSingleResult();
            res.put(nextDepartement, nombre);
        }
        return res;
    }
}
