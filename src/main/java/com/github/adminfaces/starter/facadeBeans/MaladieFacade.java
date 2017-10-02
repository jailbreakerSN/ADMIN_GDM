/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.facadeBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.github.adminfaces.starter.entities.Maladie;
import com.github.adminfaces.starter.entities.Sexe;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

/**
 *
 * @author JailbreakerSN
 */
@Stateless
public class MaladieFacade extends AbstractFacade<Maladie> {

    @PersistenceContext(unitName = "com.github.adminfaces_admin-starter_war_0.1-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaladieFacade() {
        super(Maladie.class);
    }

    public Map<Maladie, Long> nombreParMaladie() {
        Map<Maladie, Long> res = new HashMap<>();
        List<Maladie> maListe = findAll();
        for (Iterator<Maladie> iterator = maListe.iterator(); iterator.hasNext();) {
            Maladie nextMaladie = iterator.next();
            Query query = em.createNamedQuery("Maladie.countByMaladie");
            query.setParameter("id", nextMaladie.getId());
            Long nombre = (Long) query.getSingleResult();
            //System.out.println(nextMaladie + " ====== " + nombre);
            res.put(nextMaladie, nombre);
        }
        return res;
    }
    
    public Map<Maladie, Long> nombreParMaladieParSexe(int idSexe) {
        Map<Maladie, Long> res = new HashMap<>();
        List<Maladie> maListe = findAll();
        for (Iterator<Maladie> iterator = maListe.iterator(); iterator.hasNext();) {
            Maladie nextMaladie = iterator.next();
            Query query = em.createNamedQuery("Maladie.countByMaladieBySexe");
            query.setParameter("id", nextMaladie.getId());
            query.setParameter("idSexe", idSexe);
            Long nombre = (Long) query.getSingleResult();
            res.put(nextMaladie, nombre);
        }
        return res;
    }
    
    
    

}
