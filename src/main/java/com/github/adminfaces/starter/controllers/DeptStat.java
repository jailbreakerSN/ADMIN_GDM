/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.controllers;

import com.github.adminfaces.starter.entities.Departement;
import com.github.adminfaces.starter.entities.Structure;
import com.github.adminfaces.starter.facadeBeans.DepartementFacade;
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
@Named(value = "deptStat")
@SessionScoped
public class DeptStat implements Serializable {

    /**
     * Creates a new instance of DeptStat
     */
    @EJB
    private DepartementFacade DeptF;
    private PieChartModel pm_departement;

    private PieChartModel pieModel;

    public DeptStat() {
    }

    public PieChartModel getPieModel() {
        Map<Departement, Long> maMap = DeptF.nombreParDepartement();
        pieModel = new PieChartModel();
        for (Map.Entry<Departement, Long> entry : maMap.entrySet()) {
            Departement dept = entry.getKey();
            Long nombre = entry.getValue();
            pieModel.set(dept.getNomDep(), nombre);
        }
        pieModel.setTitle("Répartition des Patients selon leur maladie");
        pieModel.setLegendPosition("e");
        pieModel.setFill(true);
        pieModel.setShowDataLabels(true);
        pieModel.setExtender("ext");
        return pieModel;
    }

    public PieChartModel getPm_departement() {
        Map<Departement, Long> maMap = DeptF.nombreParDepartement();
        pm_departement = new PieChartModel();

        for (Map.Entry<Departement, Long> entry : maMap.entrySet()) {
            Departement dept = entry.getKey();
            Long nombre = entry.getValue();
            pm_departement.set(dept.getNomDep(), nombre);
        }
        pm_departement.setTitle("Répartiton des Patients selon leur departement");
        pm_departement.setLegendPosition("e");
        pm_departement.setFill(true);
        pm_departement.setShowDataLabels(true);
        pm_departement.setExtender("ext");
        return pm_departement;
    }

    public void setPm_departement(PieChartModel pm_departement) {
        this.pm_departement = pm_departement;
    }

}
