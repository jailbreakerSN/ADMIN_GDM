/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.facadeBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.github.adminfaces.starter.entities.Mesure;

/**
 *
 * @author JailbreakerSN
 */
@Stateless
public class MesureFacade extends AbstractFacade<Mesure> {

    @PersistenceContext(unitName = "com.github.adminfaces_admin-starter_war_0.1-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MesureFacade() {
        super(Mesure.class);
    }
    
}
