/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.facadeBeans;

import com.github.adminfaces.starter.entities.Region;
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
public class RegionFacade extends AbstractFacade<Region> {

    @PersistenceContext(unitName = "com.github.adminfaces_admin-starter_war_0.1-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegionFacade() {
        super(Region.class);
    }

    public Map<Region, Long> nombreParRegion() {
        Map<Region, Long> res = new HashMap<>();
        List<Region> maListe = findAll();
        for (Iterator<Region> iterator = maListe.iterator(); iterator.hasNext();) {
            Region nextRegion = iterator.next();
            Query query = em.createNamedQuery("PatientHasMaladie.findByRegion");
            query.setParameter("idRegion", nextRegion.getIDRegion());
            Long nombre = (Long) query.getSingleResult();
            res.put(nextRegion, nombre);
        }
        return res;
    }
}
