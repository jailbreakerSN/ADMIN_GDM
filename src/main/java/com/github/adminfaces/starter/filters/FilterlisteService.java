/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.filters;

import com.github.adminfaces.starter.entities.Personnel;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jailbreaker
 */
public class FilterlisteService implements Filter {

    private FilterConfig filterconfig = null;
    //Personnel pers = (Personnel) context.getExternalContext().getSessionMap().get("USER");

    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Personnel pers = (Personnel) session.getAttribute("USER");

        //Personnel P = Faces.getC
        if ("/listeServices.xhtml".equals(req.getServletPath())) {
            if (pers == null) {
                System.out.println("nullUSER");
                res.sendRedirect("login.xhtml");
            } else {
                if (pers.isMedecin() || pers.isAdminService() || pers.isSecretaire()) {
                    res.sendRedirect("index.xhtml");
                } else {
                    chain.doFilter(request, response);
                }
                
                //System.out.println(pers.getPrenom() + "------" + pers.getNom());
            }
            //chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

}
