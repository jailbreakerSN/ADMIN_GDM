/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.controllers;

import com.github.adminfaces.starter.entities.Structure;
import com.github.adminfaces.starter.facadeBeans.StructureFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;
import javax.ejb.EJB;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author FALL
 */
@Named(value = "strucStat")
@SessionScoped
public class StrucStat implements Serializable {

    /**
     * Creates a new instance of StrucStat
     */
    
     @EJB
    private StructureFacade StrucF;
    private PieChartModel pm_structures;
    
    private PieChartModel pieModel;
    
    public StrucStat() {
    }
    
    public PieChartModel getPieModel() {
        Map<Structure, Long> maMap = StrucF.nombreParStructure();
        pieModel = new PieChartModel();
        for (Map.Entry<Structure, Long> entry : maMap.entrySet()) {
            Structure structure = entry.getKey();
            Long nombre = entry.getValue();
            pieModel.set(structure.getNomStructure(), nombre);
        }
        pieModel.setTitle("Répartition des Patients selon leur maladie");
        pieModel.setLegendPosition("e");
        pieModel.setFill(true);
        pieModel.setShowDataLabels(true);
        pieModel.setExtender("ext");
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
    
    public PieChartModel getPm_structures() {
       Map<Structure, Long> maMap = StrucF.nombreParStructure();
        pm_structures = new PieChartModel();
        
        for (Map.Entry<Structure, Long> entry : maMap.entrySet()) {
            Structure structure = entry.getKey();
            Long nombre = entry.getValue();
            pm_structures.set(structure.getNomStructure(), nombre);            
        }
        pm_structures.setTitle("Répartiton des Patients selon leur Structure");
        pm_structures.setLegendPosition("e");
        pm_structures.setFill(true);
        pm_structures.setShowDataLabels(true);
        pm_structures.setExtender("ext");
        return pm_structures;
    }

    public void setPm_structures(PieChartModel pm_structures) {
        this.pm_structures = pm_structures;
    }
    
}
