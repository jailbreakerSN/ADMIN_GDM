package com.github.adminfaces.starter.controllers;

import com.github.adminfaces.starter.entities.Patient;
import com.github.adminfaces.starter.entities.Rendezvous;
import com.github.adminfaces.starter.facadeBeans.RendezvousFacade;
import com.github.adminfaces.starter.util.JsfUtil;
import com.github.adminfaces.starter.util.PaginationHelper;
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

@Named("rendezvousController")
@SessionScoped
public class RendezvousController implements Serializable {

    private Rendezvous current;
    private DataModel items = null;
    @EJB
    private RendezvousFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public RendezvousController() {
    }

    public Rendezvous getSelected() {
        if (current == null) {
            current = new Rendezvous();
            selectedItemIndex = -1;
        }
        return current;
    }

    private RendezvousFacade getFacade() {
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
        //current = (Rendezvous) getItems().getRowData();
        selectedItemIndex = -1;
        return "/rendezvous/listRV.xhtml?faces-redirect=true";
    }

    public String prepareView(Rendezvous i) {
        current = i;
        //selectedItemIndex = -1;
        return "/rendezvous/detailRV.xhtml?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new Rendezvous();
        selectedItemIndex = -1;
        return "/rendezvous/createRV.xhtml?faces-redirect=true";
    }

    public String finCreation() {
        current = new Rendezvous();
        selectedItemIndex = -1;
        return "/rendezvous/listRV.xhtml?faces-redirect=true";
    }

    public String create(Patient p) {
        try {
            current.setIdPatient(p);
            getFacade().create(current);
            p.getRendezvousList().add(current);
            JsfUtil.addSuccessMessage("SUCCES");

            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "ERREUR!!!");
            return null;
        }
    }

    public String prepareEdit() {
        current = (Rendezvous) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("SUCCES");
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "ERREUR!!!");
            return null;
        }
    }

    public String destroy() {
        current = (Rendezvous) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroy(Rendezvous i) {
        current = i;
        performDestroy();
        return "";
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RendezvousDeleted"));
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

    public Rendezvous getRendezvous(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Rendezvous.class)
    public static class RendezvousControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RendezvousController controller = (RendezvousController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rendezvousController");
            return controller.getRendezvous(getKey(value));
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
            if (object instanceof Rendezvous) {
                Rendezvous o = (Rendezvous) object;
                return getStringKey(o.getIdRv());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Rendezvous.class.getName());
            }
        }

    }

}
