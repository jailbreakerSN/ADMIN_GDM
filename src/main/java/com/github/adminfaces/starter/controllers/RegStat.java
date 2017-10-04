/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.controllers;

import com.github.adminfaces.starter.entities.Region;
import com.github.adminfaces.starter.facadeBeans.RegionFacade;
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
@Named(value = "regStat")
@SessionScoped
public class RegStat implements Serializable {

    /**
     * Creates a new instance of RegStat
     */
    @EJB
    private RegionFacade RegF;
    private PieChartModel pm_region;

    private PieChartModel pieModel;

    public RegStat() {
    }

    public PieChartModel getPieModel() {
        Map<Region, Long> maMap = RegF.nombreParRegion();
        pieModel = new PieChartModel();
        for (Map.Entry<Region, Long> entry : maMap.entrySet()) {
            Region reg = entry.getKey();
            Long nombre = entry.getValue();
            pieModel.set(reg.getNomRegion(), nombre);
        }
        pieModel.setTitle("Répartition des Patients selon leur maladie");
        pieModel.setLegendPosition("e");
        pieModel.setFill(true);
        pieModel.setShowDataLabels(true);
        pieModel.setExtender("ext");
        return pieModel;
    }

    public PieChartModel getPm_region() {
        Map<Region, Long> maMap = RegF.nombreParRegion();
        pm_region = new PieChartModel();

        for (Map.Entry<Region, Long> entry : maMap.entrySet()) {
            Region reg = entry.getKey();
            Long nombre = entry.getValue();
            pm_region.set(reg.getNomRegion(), nombre);
        }
        pm_region.setTitle("Répartiton des Patients selon leur région");
        pm_region.setLegendPosition("e");
        pm_region.setFill(true);
        pm_region.setShowDataLabels(true);
        pm_region.setExtender("ext");
        return pm_region;
    }

    public void setPm_region(PieChartModel pm_region) {
        this.pm_region = pm_region;
    }

}
