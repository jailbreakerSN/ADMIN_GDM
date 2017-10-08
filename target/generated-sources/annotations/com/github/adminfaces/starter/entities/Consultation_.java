package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.Mesure;
import com.github.adminfaces.starter.entities.Patient;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-08T15:56:53")
@StaticMetamodel(Consultation.class)
public class Consultation_ { 

    public static volatile SingularAttribute<Consultation, Date> date;
    public static volatile SingularAttribute<Consultation, String> consultationcol;
    public static volatile SingularAttribute<Consultation, Patient> patientID;
    public static volatile ListAttribute<Consultation, Mesure> mesureList;
    public static volatile SingularAttribute<Consultation, Integer> id;

}