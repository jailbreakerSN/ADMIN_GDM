package com.github.adminfaces.starter.controllers;

import com.github.adminfaces.starter.entities.Enregistrer;
import com.github.adminfaces.starter.entities.EnregistrerPK;
import com.github.adminfaces.starter.entities.Patient;
import com.github.adminfaces.starter.entities.Personnel;
import com.github.adminfaces.starter.facadeBeans.EnregistrerFacade;
import com.github.adminfaces.starter.facadeBeans.PatientFacade;
import com.github.adminfaces.starter.util.JsfUtil;
import com.github.adminfaces.starter.util.PaginationHelper;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.model.chart.PieChartModel;

@Named("patientController")
@SessionScoped
public class PatientController implements Serializable {

    private List<Patient> filteredpatient;
    private PieChartModel pieModel;

    Patient current = new Patient();
    private DataModel items = null;

    private DataModel autrePatient = null;
    private Patient APselected;
    @EJB
    private PatientFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    @EJB
    private EnregistrerFacade EnrFacade;

    FacesContext context = FacesContext.getCurrentInstance();
    Personnel pers = (Personnel) context.getExternalContext().getSessionMap().get("USER");

    //
    public PieChartModel getPieModel() {
        return pieModel;
    }

    public Patient getCurrent() {
        return current;
    }

    public void setCurrent(Patient current) {
        this.current = current;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public List<Patient> getFilteredpatient() {
        return filteredpatient;
    }

    public void setFilteredpatient(List<Patient> filteredpatient) {
        this.filteredpatient = filteredpatient;
    }

    public PatientController() {
    }

    public Patient getSelected() {
        if (current == null) {
            current = new Patient();
            selectedItemIndex = -1;

        }
        return current;
    }

    private PatientFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    //new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}
                    return new ListDataModel(getFacade().findRange(pers, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public PaginationHelper getPagination(Personnel P) {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    //new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Patient) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        FacesContext context1 = FacesContext.getCurrentInstance();
        ExternalContext ec = context1.getExternalContext();
        ec.getSessionMap().put("PATIENT", current);
        //System.out.println(p);
        return "detailspatient?faces-redirect=true";
    }

    public void getAPSelected() {
        current = (Patient) getAutrePatient().getRowData();
        System.out.println(current);
    }

    public void prepareModal() {
        current = (Patient) getItems().getRowData();
    }

    public String preparecons() {
        current = (Patient) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/constantes/test?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new Patient();
        selectedItemIndex = -1;
        return "/patients/nouveaupatient.xhtml?faces-redirect=true";
    }

    public String prepareRecherche() {
        current = new Patient();
        selectedItemIndex = -1;
        return "/patients/autrepatient.xhtml?faces-redirect=true";
    }

    public String create() {

        try {
            getFacade().create(current);
            recreateModel();
            recreatePagination();
            EnrFacade.create(new Enregistrer(new EnregistrerPK(pers.getIDService().getIDService(), current.getId()), new Date()));
            JsfUtil.addSuccessMessage("Votre Patient a été créé avec succès");
            return "patient?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Oops!! Une erreur innatendue s'est produite lors de l'insertion du patient dans la BD!!!");
            return null;
        }
    }

    public String prepareEdit() {
        current = (Patient) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit?faces-redirect=true";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("Patient mis à jour avec succès");
            return "patient?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Oops!! Une erreur s'est produite lors de la mise à jour!!!");
            return null;
        }
    }

    public String destroy() {
        current = (Patient) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "patient?faces-redirect=true";
    }

    public String enregistrerAutrePatient() {
        current = (Patient) getAutrePatient().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        System.out.println(current);
        return "#";
//        try {
//            EnrFacade.create(new Enregistrer(new EnregistrerPK(pers.getIDService().getIDService(), current.getId()), new Date()));
//            JsfUtil.addSuccessMessage("Le dossier du Patient est disponible dans votre service");
//            return "patient?faces-redirect=true";
//        } catch (Exception e) {
//            JsfUtil.addErrorMessage(e, "Oops!! Une erreur innatendue s'est produite lors de l'insertion du patient dans la BD!!!");
//            return null;
//        }
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View?faces-redirect=true";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List?faces-redirect=true";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage("Patient supprimé avec succès");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Oops!! Une erreur s'est produite lors de la suppression du patient!!!");
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    public DataModel getAutrePatient() {

        if (autrePatient == null) {
            autrePatient = getPagination(pers).createPageDataModel();
        }
        return autrePatient;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Patient getPatient(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Patient.class)
    public static class PatientControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PatientController controller = (PatientController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "patientController");
            return controller.getPatient(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Patient) {
                Patient o = (Patient) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Patient.class.getName());
            }
        }

    }

}
