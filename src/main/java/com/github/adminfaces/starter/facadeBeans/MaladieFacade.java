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
import com.github.adminfaces.starter.entities.Personnel;
import com.github.adminfaces.starter.entities.Service;
import com.github.adminfaces.starter.entities.Structure;
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
    
    public List<Maladie> findRange(Personnel P, int[] range) {
        if (P.isAdminService() || P.isSecretaire() || P.isMedecin()) {
            Query query = em.createNamedQuery("Service.findmaladies");
            query.setParameter("iDService", P.getIDService().getIDService());
            query.setMaxResults(range[1] - range[0] + 1);
            query.setFirstResult(range[0]);
            return query.getResultList();
        } else if (P.isAdminStructure()) {
            Query query = em.createNamedQuery("Service.findmaladieStructure");
            query.setParameter("idStructure", P.getIDService().getIDStructure().getIDStructure());
            query.setMaxResults(range[1] - range[0] + 1);
            query.setFirstResult(range[0]);
            return query.getResultList();
        }
        return this.findRange(range);
        
    }

    public List<Maladie> findAll(Personnel p) {
        if (p.isSecretaire() || p.isMedecin() || p.isAdminService()) {
            Query query = em.createNamedQuery("Maladie.findAllService");
            query.setParameter("idService", p.getIDService().getIDService());
            return query.getResultList();
        } else if (p.isAdminStructure()) {
            Query query = em.createNamedQuery("Maladie.findAllStructure");
            query.setParameter("idStructure", p.getIDService().getIDStructure().getIDStructure());
            return query.getResultList();
        }
        return MaladieFacade.super.findAll();

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

    public Map<Maladie, Long> nombreParMaladieService(Personnel P) {

        //System.out.println(P.getPrenom() +" ------ "+P.getIDService().getNomServiceService());
        Map<Maladie, Long> res = new HashMap<>();
        List<Maladie> maListe = findAll(P);
        for (Iterator<Maladie> iterator = maListe.iterator(); iterator.hasNext();) {
            Maladie nextMaladie = iterator.next();
            if (P.isMedecin() || P.isSecretaire() || P.isAdminService()) {
                Query query = em.createNamedQuery("Maladie.countByMaladieService");
                query.setParameter("id", nextMaladie.getId());
                query.setParameter("idService", P.getIDService().getIDService());
                Long nombre = (Long) query.getSingleResult();
                //System.out.println(nextMaladie + " ====== " + nombre);
                res.put(nextMaladie, nombre);
            } else if (P.isAdminStructure()) {
                Query query = em.createNamedQuery("Maladie.countByMaladieStructure");
                query.setParameter("id", nextMaladie.getId());
                query.setParameter("idStructure", P.getIDService().getIDStructure().getIDStructure());
                Long nombre = (Long) query.getSingleResult();
                //System.out.println(nextMaladie + " ====== " + nombre);
                res.put(nextMaladie, nombre);
            } else if (P.isAdmin()) {
                Query query = em.createNamedQuery("Maladie.countByMaladie");
                query.setParameter("id", nextMaladie.getId());
                Long nombre = (Long) query.getSingleResult();
                //System.out.println(nextMaladie + " ====== " + nombre);
                res.put(nextMaladie, nombre);
            }
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

    public Map<Maladie, Long> nombreParMaladieParSexe(Personnel P, int idSexe) {
        Map<Maladie, Long> res = new HashMap<>();
        List<Maladie> maListe = findAll(P);
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
