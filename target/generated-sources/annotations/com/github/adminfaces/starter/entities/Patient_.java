package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.Consultation;
import com.github.adminfaces.starter.entities.Enregistrer;
import com.github.adminfaces.starter.entities.PatientHasMaladie;
import com.github.adminfaces.starter.entities.Rendezvous;
import com.github.adminfaces.starter.entities.Sexe;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-07T10:46:36")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-06T17:46:48")
>>>>>>> 20fec40ced61eefbcd3573c25065aef21797bcd5
@StaticMetamodel(Patient.class)
public class Patient_ { 

    public static volatile SingularAttribute<Patient, String> password;
    public static volatile SingularAttribute<Patient, String> numeroTel;
    public static volatile SingularAttribute<Patient, String> identifiant;
    public static volatile ListAttribute<Patient, Rendezvous> rendezvousList;
    public static volatile ListAttribute<Patient, PatientHasMaladie> patientHasMaladieList;
    public static volatile SingularAttribute<Patient, Date> dateNaiss;
    public static volatile SingularAttribute<Patient, Integer> id;
    public static volatile SingularAttribute<Patient, String> nom;
    public static volatile SingularAttribute<Patient, String> prenom;
    public static volatile ListAttribute<Patient, Enregistrer> enregistrerList;
    public static volatile SingularAttribute<Patient, Sexe> codeSexe;
    public static volatile ListAttribute<Patient, Consultation> consultationList;

}