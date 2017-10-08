package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.Departement;
import com.github.adminfaces.starter.entities.Service;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-08T15:56:53")
@StaticMetamodel(Structure.class)
public class Structure_ { 

    public static volatile SingularAttribute<Structure, Departement> iDDepartement;
    public static volatile SingularAttribute<Structure, String> nomStructure;
    public static volatile SingularAttribute<Structure, Integer> iDStructure;
    public static volatile ListAttribute<Structure, Service> serviceList;
    public static volatile SingularAttribute<Structure, String> description;

}