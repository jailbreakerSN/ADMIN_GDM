/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.facadeBeans;

import com.github.adminfaces.starter.entities.Structure;
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
public class StructureFacade extends AbstractFacade<Structure> {

    @PersistenceContext(unitName = "com.github.adminfaces_admin-starter_war_0.1-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StructureFacade() {
        super(Structure.class);
    }
    
     public Map<Structure, Long> nombreParStructure() {
        Map<Structure, Long> res = new HashMap<>();
        List<Structure> maListe = findAll();
        for (Iterator<Structure> iterator = maListe.iterator(); iterator.hasNext();) {
            Structure nextStructure = iterator.next();
            Query query = em.createNamedQuery("PatientHasMaladie.findByStructure");
            query.setParameter("iDStructure", nextStructure.getIDStructure());
            Long nombre = (Long) query.getSingleResult();
            res.put(nextStructure, nombre);
        }
        return res;
    }
    
}
