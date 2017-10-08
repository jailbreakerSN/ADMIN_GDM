package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.Region;
import com.github.adminfaces.starter.entities.Structure;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-08T12:08:32")
@StaticMetamodel(Departement.class)
public class Departement_ { 

    public static volatile SingularAttribute<Departement, Integer> iDDepartement;
    public static volatile SingularAttribute<Departement, String> nomDep;
    public static volatile ListAttribute<Departement, Structure> structureList;
    public static volatile SingularAttribute<Departement, Region> iDRegion;

}