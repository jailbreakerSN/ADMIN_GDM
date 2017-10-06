package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.Departement;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-06T17:46:48")
@StaticMetamodel(Region.class)
public class Region_ { 

    public static volatile ListAttribute<Region, Departement> departementList;
    public static volatile SingularAttribute<Region, String> nomRegion;
    public static volatile SingularAttribute<Region, Integer> iDRegion;

}