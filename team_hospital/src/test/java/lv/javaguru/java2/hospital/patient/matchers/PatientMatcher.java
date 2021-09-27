package lv.javaguru.java2.hospital.patient.matchers;

import lv.javaguru.java2.hospital.domain.Patient;
import org.mockito.ArgumentMatcher;

public class PatientMatcher implements ArgumentMatcher<Patient> {
    private String name;
    private String surname;
    private String personalCode;

    public PatientMatcher(String name, String surname, String personalCode) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
    }

    @Override
    public boolean matches(Patient patient) {
        return patient.getName().equals(name)
                && patient.getSurname().equals(surname)
                && patient.getPersonalCode().equals(personalCode);
    }
}
