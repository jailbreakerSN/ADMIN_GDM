package com.github.adminfaces.starter.controllers;

import com.github.adminfaces.starter.entities.Consultation;
import com.github.adminfaces.starter.entities.Patient;
import com.github.adminfaces.starter.facadeBeans.ConsultationFacade;
import com.github.adminfaces.starter.util.JsfUtil;
import com.github.adminfaces.starter.util.PaginationHelper;

import java.io.Serializable;
import java.util.List;
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

@Named("consultationController")
@SessionScoped
public class ConsultationController implements Serializable {

    private Consultation current;
    private DataModel items = null;
    @EJB
    private ConsultationFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ConsultationController() {
    }

    public Consultation getSelected() {
        if (current == null) {
            current = new Consultation();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ConsultationFacade getFacade() {
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

    public String prepareUpdate(Consultation i) {
        current = i;
        // selectedItemIndex= pagination.getPageFirstItem() + pagination.getPageLastItem();
        return "/consultation/update.xhtml?faces-redirect=true";
    }

    public String prepareView() {
        //current = (Consultation) getItems().getRowData();
        selectedItemIndex = -1;
        return "/consultation/consult.xhtml?faces-redirect=true";
    }

    public String prepareView1(Consultation i) {
        current = i;
        // selectedItemIndex= pagination.getPageFirstItem() + pagination.getPageLastItem();
        return "/consultation/detailconsult.xhtml?faces-redirect=true";
    }
    
    public void prepareMod(Consultation C){
        current = C;
        
    }
    public void prepareModal() {
        current = (Consultation) getItems().getRowData();
    }

    public String prepareCreate() {
        current = new Consultation();
        selectedItemIndex = -1;
        return "/consultation/Create.xhtml?faces-redirect=true";
    }

    public String create(Patient p) {
        try {
            current.setPatientID(p);
            getFacade().create(current);
            p.getConsultationList().add(current);
            JsfUtil.addSuccessMessage("ConsultationCreated");
            recreateModel();
            recreatePagination();
            return "/consultation/consult.xhtml?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
            return null;
        }
    }

    public String prepareEdit() {
        current = (Consultation) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String prepareUpdate(){
        current = new Consultation();
     
        return "consult.xhtml?faces-redirect=true";
    }
    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("ConsultationUpdated");
            return prepareUpdate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e,"PersistenceErrorOccured");
            return null;
        }
    }

    public String destroy(Consultation C,Patient p) {
        
        current = C;
        performDestroy();
        p.getConsultationList().remove(C);
        recreatePagination();
        recreateModel();
        return "consult.xhtml?faces-redirect=true";
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
            JsfUtil.addSuccessMessage("ConsultationDeleted");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
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

    public Consultation getConsultation(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Consultation.class)
    public static class ConsultationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConsultationController controller = (ConsultationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "consultationController");
            return controller.getConsultation(getKey(value));
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
            if (object instanceof Consultation) {
                Consultation o = (Consultation) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Consultation.class.getName());
            }
        }

    }
    
    public List<Consultation> ListCons(Patient P){
        return P.getConsultationList();
    }
}
