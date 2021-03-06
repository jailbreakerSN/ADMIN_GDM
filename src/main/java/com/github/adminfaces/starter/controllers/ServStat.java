/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.controllers;

import com.github.adminfaces.starter.entities.Maladie;
import com.github.adminfaces.starter.entities.Personnel;
import com.github.adminfaces.starter.entities.Service;
import com.github.adminfaces.starter.entities.Sexe;
import com.github.adminfaces.starter.facadeBeans.MaladieFacade;
import com.github.adminfaces.starter.facadeBeans.ServiceFacade;
import com.github.adminfaces.starter.facadeBeans.SexeFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author JailbreakerSN
 */
@Named(value = "servStat")
@SessionScoped
public class ServStat implements Serializable {

    @EJB
    private MaladieFacade mf;
    private PieChartModel pieModel;
    private BarChartModel barModel;
    private PieChartModel pModel;

    @EJB
    private SexeFacade sf;
    private PieChartModel pm_Sexes;

    @EJB
    private ServiceFacade servF;
    private PieChartModel pm_Services;

    @EJB
    private ServiceFacade serfac;

    /**
     * Creates a new instance of Statistiques
     */
    public ServStat() {
    }

    FacesContext context = FacesContext.getCurrentInstance();
    Personnel pers = (Personnel) context.getExternalContext().getSessionMap().get("USER");

    public PieChartModel getPieModel() {
        if (pers.isAdminStructure() || pers.isAdmin()) {
            FacesContext context1 = FacesContext.getCurrentInstance();
            Service service = (Service) context1.getExternalContext().getSessionMap().get("SERVSTAT");
            if (service != null) {
                Personnel adminService = serfac.getAdmin(service);
                System.out.println(adminService);
                pers = adminService;
                //System.out.println("Le chef de ce service: " + service.getNomServiceService() + " est " + adminService);
            } else {
                System.out.println("Service NUL");
            }
        }
        Map<Service, Long> maMap = servF.nombreParService(pers);
        pieModel = new PieChartModel();
        for (Map.Entry<Service, Long> entry : maMap.entrySet()) {
            Service service = entry.getKey();
            Long nombre = entry.getValue();
            pieModel.set(service.getNomServiceService(), nombre);
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

    public PieChartModel getpModel() {
        Map<Service, Long> maMap = servF.nombreParService(pers);
        pModel = new PieChartModel();
        for (Map.Entry<Service, Long> entry : maMap.entrySet()) {
            Service maladie = entry.getKey();
            Long nombre = entry.getValue();
            pModel.set(maladie.getNomServiceService(), nombre);
        }
        pModel.setTitle("Répartition des Patients selon leur maladie");
        pModel.setLegendPosition("e");
        pModel.setFill(true);
        pModel.setShowDataLabels(true);
        pModel.setExtender("ext");
        return pModel;

//        Map<Maladie, Long> maMap = mf.nombreParMaladieService(pers);
//        pModel = new PieChartModel();
//        for (Map.Entry<Maladie, Long> entry : maMap.entrySet()) {
//            Maladie maladie = entry.getKey();
//            Long nombre = entry.getValue();
//            pModel.set(maladie.getNom(), nombre);
//        }
//        pModel.setTitle("Répartition des Patients selon leur maladie");
//        pModel.setLegendPosition("e");
//        pModel.setFill(true);
//        pModel.setShowDataLabels(true);
//        pModel.setExtender("ext");
//        return pModel;
    }

    public void setpModel(PieChartModel pModel) {
        this.pModel = pModel;
    }

    public BarChartModel getBarModel() {
        Map<Maladie, Long> maMapHomme = mf.nombreParMaladieParSexe(1);
        barModel = new BarChartModel();

        ChartSeries hommes = new ChartSeries("HOMMES");
        for (Map.Entry<Maladie, Long> entry : maMapHomme.entrySet()) {
            Maladie maladie = entry.getKey();
            Long nombre = entry.getValue();
            hommes.set(maladie.getNom(), nombre);
        }

        Map<Maladie, Long> maMapFemme = mf.nombreParMaladieParSexe(2);
        ChartSeries femmes = new ChartSeries("FEMMES");
        for (Map.Entry<Maladie, Long> entry : maMapFemme.entrySet()) {
            Maladie maladie = entry.getKey();
            Long nombre = entry.getValue();
            femmes.set(maladie.getNom(), nombre);
        }

        barModel.addSeries(hommes);
        barModel.addSeries(femmes);
        barModel.setTitle("Répartition des Maladies selon le Genre");
        barModel.setLegendPosition("ne");
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Maladies");
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Nombre");
        yAxis.setMin(0);
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public PieChartModel getPm_Sexes() {
        Map<Sexe, Long> maMap = sf.nombreParSexe();
        pm_Sexes = new PieChartModel();
        for (Map.Entry<Sexe, Long> entry : maMap.entrySet()) {
            Sexe sexe = entry.getKey();
            Long nombre = entry.getValue();
            pm_Sexes.set(sexe.getLibelle(), nombre);
        }
        pm_Sexes.setTitle("Répartiton des Patients selon leur sexe");
        pm_Sexes.setLegendPosition("e");
        pm_Sexes.setFill(true);
        pm_Sexes.setShowDataLabels(true);
        pm_Sexes.setExtender("ext");
        return pm_Sexes;
    }

    public void setPm_Sexes(PieChartModel pm_Sexes) {
        this.pm_Sexes = pm_Sexes;
    }

    public PieChartModel getPm_Services() {
        Map<Service, Long> maMap = servF.nombreParService(pers);
        pm_Services = new PieChartModel();

        for (Map.Entry<Service, Long> entry : maMap.entrySet()) {
            Service service = entry.getKey();
            Long nombre = entry.getValue();
            pm_Services.set(service.getNomServiceService(), nombre);
        }
        pm_Services.setTitle("Répartiton des Patients selon leur Service");
        pm_Services.setLegendPosition("e");
        pm_Services.setFill(true);
        pm_Services.setShowDataLabels(true);
        pm_Services.setExtender("ext");
        return pm_Services;
    }

    public void setPm_Services(PieChartModel pm_Services) {
        this.pm_Services = pm_Services;
    }

}
