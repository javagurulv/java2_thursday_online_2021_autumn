package lv.javaguru.java2.hospital.visits.core.services.validators;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.visits.core.request.AddPatientVisitRequest;
import lv.javaguru.java2.hospital.visits.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AddPatientVisitValidatorTest {

    PatientDatabaseImpl patientDatabase = new PatientDatabaseImpl();
    DoctorDatabaseImpl doctorDatabase = new DoctorDatabaseImpl();
    AddPatientVisitValidator addPatientVisitValidator = new AddPatientVisitValidator(patientDatabase, doctorDatabase);

    @Test
    public void shouldReturnPersonalCodeError(){
        AddPatientVisitRequest addPatientVisitRequest = new AddPatientVisitRequest(
                "",
                "name",
                "surname",
                "12/12/21 12:00");
        List<CoreError> errorList = addPatientVisitValidator.validate(addPatientVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get(0).getField(), "Patient personal code");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldDoctorNameError(){
        AddPatientVisitRequest addPatientVisitRequest = new AddPatientVisitRequest(
                "1234",
                "",
                "surname",
                "12/12/21 12:00");
        List<CoreError> errorList = addPatientVisitValidator.validate(addPatientVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get(0).getField(), "Doctor name");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnDoctorSurnameError(){
        AddPatientVisitRequest addPatientVisitRequest = new AddPatientVisitRequest(
                "1234",
                "name",
                "",
                "12/12/21 12:00");
        List<CoreError> errorList = addPatientVisitValidator.validate(addPatientVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get(0).getField(), "Doctor surname");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnDateError(){
        AddPatientVisitRequest addPatientVisitRequest = new AddPatientVisitRequest(
                "1234",
                "name",
                "surname",
                "");
        List<CoreError> errorList = addPatientVisitValidator.validate(addPatientVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get(0).getField(), "Visit date");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnPatientDoesNotExistError(){
        doctorDatabase.addDoctor(new Doctor("name", "surname", "speciality"));
        AddPatientVisitRequest addPatientVisitRequest = new AddPatientVisitRequest(
                "1234",
                "name",
                "surname",
                "12/12/2021 12:00");
        List<CoreError> errorList = addPatientVisitValidator.validate(addPatientVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get(0).getField(), "Patient");
        assertEquals(errorList.get(0).getDescription(), "does not exist!");
    }

    @Test
    public void shouldReturnDoctorDoesNotExistError(){
        patientDatabase.add(new Patient("name", "surname", "1234"));
        AddPatientVisitRequest addPatientVisitRequest = new AddPatientVisitRequest(
                "1234",
                "name",
                "surname",
                "12/12/2021 12:00");
        List<CoreError> errorList = addPatientVisitValidator.validate(addPatientVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get(0).getField(), "Doctor");
        assertEquals(errorList.get(0).getDescription(), "does not exist!");
    }
}