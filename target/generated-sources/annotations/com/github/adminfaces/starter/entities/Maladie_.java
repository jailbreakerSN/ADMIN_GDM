package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.PatientHasMaladie;
import com.github.adminfaces.starter.entities.Service;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-07T14:43:41")
@StaticMetamodel(Maladie.class)
public class Maladie_ { 

    public static volatile ListAttribute<Maladie, PatientHasMaladie> patientHasMaladieList;
    public static volatile SingularAttribute<Maladie, Service> iDService;
    public static volatile SingularAttribute<Maladie, String> description;
    public static volatile SingularAttribute<Maladie, Integer> id;
    public static volatile SingularAttribute<Maladie, String> nom;

}