package lv.javaguru.java2.hospital.doctor.matchers;

import lv.javaguru.java2.hospital.domain.Doctor;
import org.mockito.ArgumentMatcher;

public class DoctorMatcher implements ArgumentMatcher<Doctor> {
    private String name;
    private String surname;
    private String speciality;

    public DoctorMatcher(String name, String surname, String speciality) {
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
    }

    @Override
    public boolean matches(Doctor doctor) {
        return doctor.getName().equals(name)
                && doctor.getSurname().equals(surname)
                && doctor.getSpeciality().equals(speciality);
    }
}
