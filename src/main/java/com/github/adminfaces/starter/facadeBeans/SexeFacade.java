/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.facadeBeans;

import com.github.adminfaces.starter.entities.Sexe;
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
 * @author JailbreakerSN
 */
@Stateless
public class SexeFacade extends AbstractFacade<Sexe> {

    @PersistenceContext(unitName = "com.github.adminfaces_admin-starter_war_0.1-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SexeFacade() {
        super(Sexe.class);
    }
    
    public Map<Sexe, Long> nombreParSexe() {
        Map<Sexe, Long> res = new HashMap<>();
        List<Sexe> maListe = findAll();
        for (Iterator<Sexe> iterator = maListe.iterator(); iterator.hasNext();) {
            Sexe nextSexe = iterator.next();
            Query query = em.createNamedQuery("Patient.countBySexe");
            query.setParameter("id", nextSexe.getIdSexe());
            Long nombre = (Long) query.getSingleResult();
            //System.out.println(nextMaladie + " ====== " + nombre);
            res.put(nextSexe, nombre);
        }
        return res;
    }
    
}
