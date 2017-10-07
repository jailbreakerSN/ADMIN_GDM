package com.github.adminfaces.starter.entities;

import com.github.adminfaces.starter.entities.Maladie;
import com.github.adminfaces.starter.entities.Patient;
import com.github.adminfaces.starter.entities.PatientHasMaladiePK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-07T10:46:36")
@StaticMetamodel(PatientHasMaladie.class)
public class PatientHasMaladie_ { 

    public static volatile SingularAttribute<PatientHasMaladie, Patient> patient;
    public static volatile SingularAttribute<PatientHasMaladie, PatientHasMaladiePK> patientHasMaladiePK;
    public static volatile SingularAttribute<PatientHasMaladie, Maladie> maladie;

}