package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.Enregistrer;
import com.github.adminfaces.starter.entities.Maladie;
import com.github.adminfaces.starter.entities.Personnel;
import com.github.adminfaces.starter.entities.Structure;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-07T10:46:36")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-06T17:46:48")
>>>>>>> 20fec40ced61eefbcd3573c25065aef21797bcd5
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