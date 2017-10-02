package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.Rendezvous;
import com.github.adminfaces.starter.entities.Service;
import com.github.adminfaces.starter.entities.Typepersonnel;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-02T16:40:14")
@StaticMetamodel(Personnel.class)
public class Personnel_ { 

    public static volatile SingularAttribute<Personnel, Typepersonnel> iDTypeEmploye;
    public static volatile SingularAttribute<Personnel, String> loginPersonnel;
    public static volatile SingularAttribute<Personnel, Date> dateNaissPersonnel;
    public static volatile ListAttribute<Personnel, Rendezvous> rendezvousList;
    public static volatile SingularAttribute<Personnel, Service> iDService;
    public static volatile SingularAttribute<Personnel, String> adresseMailPersonnel;
    public static volatile SingularAttribute<Personnel, Integer> iDPersonnel;
    public static volatile SingularAttribute<Personnel, String> prenom;
    public static volatile SingularAttribute<Personnel, String> nom;
    public static volatile SingularAttribute<Personnel, String> numeroTelPersonnel;
    public static volatile SingularAttribute<Personnel, String> passwordPersonnel;

}