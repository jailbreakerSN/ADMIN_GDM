package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.Mesure;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-07T10:46:36")
@StaticMetamodel(GrandeurPhysique.class)
public class GrandeurPhysique_ { 

    public static volatile SingularAttribute<GrandeurPhysique, String> description;
    public static volatile ListAttribute<GrandeurPhysique, Mesure> mesureList;
    public static volatile SingularAttribute<GrandeurPhysique, Integer> id;
    public static volatile SingularAttribute<GrandeurPhysique, String> nom;

}