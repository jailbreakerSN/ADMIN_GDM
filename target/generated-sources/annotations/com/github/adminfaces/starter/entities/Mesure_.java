package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.Consultation;
import com.github.adminfaces.starter.entities.GrandeurPhysique;
import com.github.adminfaces.starter.entities.MesurePK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-07T10:46:36")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-06T17:46:48")
>>>>>>> 20fec40ced61eefbcd3573c25065aef21797bcd5
@StaticMetamodel(Mesure.class)
public class Mesure_ { 

    public static volatile SingularAttribute<Mesure, MesurePK> mesurePK;
    public static volatile SingularAttribute<Mesure, Double> valeur;
    public static volatile SingularAttribute<Mesure, Consultation> consultation;
    public static volatile SingularAttribute<Mesure, GrandeurPhysique> grandeurPhysique;

}