package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.EnregistrerPK;
import com.github.adminfaces.starter.entities.Patient;
import com.github.adminfaces.starter.entities.Service;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-07T10:46:36")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-06T17:46:48")
>>>>>>> 20fec40ced61eefbcd3573c25065aef21797bcd5
@StaticMetamodel(Enregistrer.class)
public class Enregistrer_ { 

    public static volatile SingularAttribute<Enregistrer, Date> dateCreation;
    public static volatile SingularAttribute<Enregistrer, Patient> patient;
    public static volatile SingularAttribute<Enregistrer, Service> service;
    public static volatile SingularAttribute<Enregistrer, EnregistrerPK> enregistrerPK;

}