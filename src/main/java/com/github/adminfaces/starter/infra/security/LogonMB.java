package com.github.adminfaces.starter.infra.security;

import com.github.adminfaces.template.session.AdminSession;
import org.omnifaces.util.Faces;
import com.github.adminfaces.starter.facadeBeans.UserFacade;
import com.github.adminfaces.starter.entities.User;
import com.github.adminfaces.starter.controllers.UserController;
import com.github.adminfaces.starter.entities.Personnel;
import com.github.adminfaces.starter.facadeBeans.PersonnelFacade;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

/**
 * Created by rmpestano on 12/20/14.
 *
 * This is just a login example.
 *
 * AdminSession uses isLoggedIn to determine if user must be redirect to login
 * page or not. By default AdminSession isLoggedIn always resolves to true so it
 * will not try to redirect user.
 *
 * If you already have your authorization mechanism which controls when user
 * must be redirect to initial page or logon you can skip this class.
 */
@Named
@SessionScoped
@Specializes
public class LogonMB extends AdminSession implements Serializable {

    private String currentUser;
    private String email;
    private String password;
    private boolean remember;
    @EJB
    private UserFacade uf;

    @EJB
    private PersonnelFacade pf;

    private UserController uc;

    private User u;
    private Personnel p;

    public void login() throws IOException {
        List<User> us = uf.findAll();
        currentUser = email;

        FacesContext context = FacesContext.getCurrentInstance();

//        Iterator<User> it = us.iterator();
//        while (it.hasNext()) {
//            u = it.next();
//            if (Objects.equals(u.getUsername(), email) && Objects.equals(u.getPassword(), password)) {
//                addDetailMessage("Logged in successfully as <b>" + email + "</b>");
//                Faces.getExternalContext().getFlash().setKeepMessages(true);
//                Faces.redirect("index.xhtml");
//            }
//        }
        List<Personnel> ps = pf.findAll();
        Iterator<Personnel> ip = ps.iterator();
        while (ip.hasNext()) {
            p = ip.next();
            if (Objects.equals(p.getAdresseMailPersonnel(), email) && Objects.equals(p.getPasswordPersonnel(), password)) {
                Faces.getContext().getExternalContext().getSessionMap().put("USER", p);
                Faces.redirect("index.xhtml");
                
            }

        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!!", "Erreur sur le login et/ou le mot de passe!"));
    }

    @Override
    public boolean isLoggedIn() {

        return currentUser != null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
