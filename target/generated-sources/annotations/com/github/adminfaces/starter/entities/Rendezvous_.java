package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.Patient;
import com.github.adminfaces.starter.entities.Personnel;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-03T19:03:11")
@StaticMetamodel(Rendezvous.class)
public class Rendezvous_ { 

    public static volatile SingularAttribute<Rendezvous, Date> dateDebut;
    public static volatile SingularAttribute<Rendezvous, String> titre;
    public static volatile SingularAttribute<Rendezvous, Personnel> iDPersonnel;
    public static volatile SingularAttribute<Rendezvous, Patient> idPatient;
    public static volatile SingularAttribute<Rendezvous, Date> dateFin;
    public static volatile SingularAttribute<Rendezvous, Integer> idRv;

}