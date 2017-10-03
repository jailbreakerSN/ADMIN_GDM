package com.github.adminfaces.starter.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-03T19:03:11")
@StaticMetamodel(PatientHasMaladiePK.class)
public class PatientHasMaladiePK_ { 

    public static volatile SingularAttribute<PatientHasMaladiePK, Integer> patientID;
    public static volatile SingularAttribute<PatientHasMaladiePK, Date> dateDiagnostic;
    public static volatile SingularAttribute<PatientHasMaladiePK, Integer> maladieID;

}