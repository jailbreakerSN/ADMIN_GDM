/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.filters;

import com.github.adminfaces.starter.entities.Patient;
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
public class FilterPatient implements Filter {

    private FilterConfig filterconfig = null;

    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterconfig = filterconfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Patient patient = (Patient) session.getAttribute("PATIENT");       
        

        if ("/patients/detailspatient.xhtml".equals(req.getServletPath())) {            
            if (patient == null) {
                System.out.println("Requete recu");
                res.sendRedirect("patient.xhtml");
            } else {
                System.out.println(patient + "Vide");
                chain.doFilter(request, response);
            }

        } else {
            System.out.println("ICI");
            chain.doFilter(request, response);
        }
    }

}
