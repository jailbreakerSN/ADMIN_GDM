package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.PatientHasMaladie;
import com.github.adminfaces.starter.entities.Service;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-07T10:46:36")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-06T17:46:48")
>>>>>>> 20fec40ced61eefbcd3573c25065aef21797bcd5
@StaticMetamodel(Maladie.class)
public class Maladie_ { 

    public static volatile ListAttribute<Maladie, PatientHasMaladie> patientHasMaladieList;
    public static volatile SingularAttribute<Maladie, Service> iDService;
    public static volatile SingularAttribute<Maladie, String> description;
    public static volatile SingularAttribute<Maladie, Integer> id;
    public static volatile SingularAttribute<Maladie, String> nom;

}