package com.github.adminfaces.starter.controllers;

import com.github.adminfaces.starter.entities.Maladie;
import com.github.adminfaces.starter.facadeBeans.MaladieFacade;
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

@Named("maladieController")
@SessionScoped
public class MaladieController implements Serializable {

    private Maladie current;
    private DataModel items = null;

    public Maladie getCurrent() {
        return current;
    }

    public void setCurrent(Maladie current) {
        this.current = current;
    }
    @EJB
    private MaladieFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public MaladieController() {
    }

    public Maladie getSelected() {
        if (current == null) {
            current = new Maladie();
            selectedItemIndex = -1;
        }
        return current;
    }

    private MaladieFacade getFacade() {
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
        current = (Maladie) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "detailmaladie?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new Maladie();
        selectedItemIndex = -1;
        return "nouvellemaladie?faces-redirect=true";
    }
    
      public String prepareCreate1() {
       
        return "maladies?faces-redirect=true";
    }
      
    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage("MaladieCreated");
            recreateModel();
            recreatePagination();
            return prepareCreate1();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e,"PersistenceErrorOccured");
            return null;
        }
    }

    public String prepareEdit() {
        current = (Maladie) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("Maladie mis à jour avec succès");
            return "maladies?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Oops!! Une erreur s'est produite lors de la mise à jour!!!");
            return null;
        }
    }

    public String destroy() {
        current = (Maladie) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "maladies?faces-redirect=true";
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
            return "List?faces-redirect=true";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage("MaladieDeleted");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e,"PersistenceErrorOccured");
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
        return "List?faces-redirect=true";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List?faces-redirect=true";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Maladie getMaladie(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Maladie.class)
    public static class MaladieControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaladieController controller = (MaladieController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "maladieController");
            return controller.getMaladie(getKey(value));
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
            if (object instanceof Maladie) {
                Maladie o = (Maladie) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Maladie.class.getName());
            }
        }

    }

}
