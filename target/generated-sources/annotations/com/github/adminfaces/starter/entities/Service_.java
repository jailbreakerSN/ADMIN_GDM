package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.Enregistrer;
import com.github.adminfaces.starter.entities.Maladie;
import com.github.adminfaces.starter.entities.Personnel;
import com.github.adminfaces.starter.entities.Structure;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-07T14:43:41")
@StaticMetamodel(Service.class)
public class Service_ { 

    public static volatile ListAttribute<Service, Maladie> maladieList;
    public static volatile SingularAttribute<Service, Structure> iDStructure;
    public static volatile ListAttribute<Service, Personnel> personnelList;
    public static volatile SingularAttribute<Service, Integer> iDService;
    public static volatile SingularAttribute<Service, String> descriptionService;
    public static volatile SingularAttribute<Service, String> nomServiceService;
    public static volatile ListAttribute<Service, Enregistrer> enregistrerList;

}