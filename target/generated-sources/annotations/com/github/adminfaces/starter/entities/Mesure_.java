package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.Consultation;
import com.github.adminfaces.starter.entities.GrandeurPhysique;
import com.github.adminfaces.starter.entities.MesurePK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-03T15:30:21")
@StaticMetamodel(Mesure.class)
public class Mesure_ { 

    public static volatile SingularAttribute<Mesure, MesurePK> mesurePK;
    public static volatile SingularAttribute<Mesure, Double> valeur;
    public static volatile SingularAttribute<Mesure, Consultation> consultation;
    public static volatile SingularAttribute<Mesure, GrandeurPhysique> grandeurPhysique;

}