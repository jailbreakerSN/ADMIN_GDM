package com.github.adminfaces.starter.controllers;

import com.github.adminfaces.starter.entities.Consultation;
import com.github.adminfaces.starter.entities.GrandeurPhysique;
import com.github.adminfaces.starter.entities.Mesure;
import com.github.adminfaces.starter.facadeBeans.MesureFacade;
import com.github.adminfaces.starter.util.JsfUtil;
import com.github.adminfaces.starter.util.PaginationHelper;
import com.github.adminfaces.starter.entities.MesurePK;

import java.io.Serializable;
import java.util.List;
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

@Named("mesureController")
@SessionScoped
public class MesureController implements Serializable {

    private Mesure current;
    private DataModel items = null;
    @EJB
    private MesureFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public MesureController() {
    }

    public Mesure getSelected() {
        if (current == null) {
            current = new Mesure();
            current.setMesurePK(new MesurePK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private MesureFacade getFacade() {
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
        current = (Mesure) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Mesure();
        current.setMesurePK(new MesurePK());
        selectedItemIndex = -1;
        return "/consultation/detailconsult.xhtml?faces-redirect=true";
    }

    public String create(Consultation c) {
        try {
            current.getMesurePK().setConsultationID(c.getId());
            current.getMesurePK().setGrandeurphysiqueID(current.getGrandeurPhysique().getId());
            getFacade().create(current);
            c.getMesureList().add(current);
            selectedItemIndex = -1;
            recreateModel();
            recreatePagination();
            JsfUtil.addSuccessMessage("MesureCreated");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
            return null;
        }

    }

    public String prepareEdit() {
        selectedItemIndex = -1;
        return "detailconsult?faces-redirect=true";
    }

    public void prep(Mesure M) {
       current = M;
    }

    public String update(GrandeurPhysique G, Consultation C) {
        try {
            current.getMesurePK().setConsultationID(C.getId());
            current.getMesurePK().setGrandeurphysiqueID(G.getId());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("MesureUpdated");
            return prepareEdit();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
            return null;
        }
    }

    public String destroy(GrandeurPhysique G, Consultation C) {
        current.getMesurePK().setConsultationID(C.getId());
        current.getMesurePK().setGrandeurphysiqueID(G.getId());
        // selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        C.getMesureList().remove(current);
        recreatePagination();
        recreateModel();
        return "/consultation/detailconsult?faces-redirect=true";
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
            JsfUtil.addSuccessMessage("MesureDeleted");
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

    public Mesure getMesure(MesurePK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Mesure.class)
    public static class MesureControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MesureController controller = (MesureController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mesureController");
            return controller.getMesure(getKey(value));
        }

        MesurePK getKey(String value) {
            MesurePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new MesurePK();
            key.setConsultationID(Integer.parseInt(values[0]));
            key.setGrandeurphysiqueID(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(MesurePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getConsultationID());
            sb.append(SEPARATOR);
            sb.append(value.getGrandeurphysiqueID());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Mesure) {
                Mesure o = (Mesure) object;
                return getStringKey(o.getMesurePK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Mesure.class.getName());
            }
        }

    }

    public List<Mesure> ListMes(Consultation C) {
        return C.getMesureList();
    }

}
