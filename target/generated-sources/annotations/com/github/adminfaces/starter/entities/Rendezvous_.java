package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.Patient;
import com.github.adminfaces.starter.entities.Personnel;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-07T10:46:36")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-06T17:46:48")
>>>>>>> 20fec40ced61eefbcd3573c25065aef21797bcd5
@StaticMetamodel(Rendezvous.class)
public class Rendezvous_ { 

    public static volatile SingularAttribute<Rendezvous, Date> dateDebut;
    public static volatile SingularAttribute<Rendezvous, String> titre;
    public static volatile SingularAttribute<Rendezvous, Personnel> iDPersonnel;
    public static volatile SingularAttribute<Rendezvous, Patient> idPatient;
    public static volatile SingularAttribute<Rendezvous, Integer> idRv;

}