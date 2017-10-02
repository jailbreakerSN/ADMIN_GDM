package com.github.adminfaces.starter.controllers;

import com.github.adminfaces.starter.entities.PatientHasMaladie;
import com.github.adminfaces.starter.facadeBeans.PatientHasMaladieFacade;
import com.github.adminfaces.starter.util.JsfUtil;
import com.github.adminfaces.starter.util.PaginationHelper;
import com.github.adminfaces.starter.entities.PatientHasMaladiePK;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("patientHasMaladieCon")
@SessionScoped
public class PatientHasMaladieCon implements Serializable {

    private PatientHasMaladie current;
    private DataModel items = null;
    @EJB
    private PatientHasMaladieFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    private PatientController p;
    
    public PatientHasMaladieCon() {
    }

    public PatientHasMaladie getSelected() {
        if (current == null) {
            current = new PatientHasMaladie();
            current.setPatientHasMaladiePK(new PatientHasMaladiePK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private PatientHasMaladieFacade getFacade() {
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
        current = (PatientHasMaladie) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        PatientHasMaladiePK patient = new PatientHasMaladiePK();
        current = new PatientHasMaladie();
        current.setPatientHasMaladiePK(new PatientHasMaladiePK());
        selectedItemIndex = patient.getPatientID();
        return "/patientHasMaladie/Create.xhtml?faces-redirect=true";
    }

    public String create() {
        
        try {
            current.getPatientHasMaladiePK().setPatientID(p.getSelected().getId());
            current.getPatientHasMaladiePK().setMaladieID(current.getMaladie().getId());
            getFacade().create(current);
            JsfUtil.addSuccessMessage("PatientHasMaladieCreated");
            return "/maladies/maladies.xhtml?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage("PersistenceErrorOccured");
            return null;
        }
    }

    public String prepareEdit() {
        current = (PatientHasMaladie) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getPatientHasMaladiePK().setPatientID(current.getPatient().getId());
            current.getPatientHasMaladiePK().setMaladieID(current.getMaladie().getId());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PatientHasMaladieUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (PatientHasMaladie) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PatientHasMaladieDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
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

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public PatientHasMaladie getPatientHasMaladie(PatientHasMaladiePK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = PatientHasMaladie.class)
    public static class PatientHasMaladieControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PatientHasMaladieCon controller = (PatientHasMaladieCon) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "patientHasMaladieController");
            return controller.getPatientHasMaladie(getKey(value));
        }

        PatientHasMaladiePK getKey(String value) {
            PatientHasMaladiePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new PatientHasMaladiePK();
            key.setPatientID(Integer.parseInt(values[0]));
            key.setMaladieID(Integer.parseInt(values[1]));
            key.setDateDiagnostic(java.sql.Date.valueOf(values[2]));
            return key;
        }

        String getStringKey(PatientHasMaladiePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getPatientID());
            sb.append(SEPARATOR);
            sb.append(value.getMaladieID());
            sb.append(SEPARATOR);
            sb.append(value.getDateDiagnostic());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PatientHasMaladie) {
                PatientHasMaladie o = (PatientHasMaladie) object;
                return getStringKey(o.getPatientHasMaladiePK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PatientHasMaladie.class.getName());
            }
        }

    }

}
