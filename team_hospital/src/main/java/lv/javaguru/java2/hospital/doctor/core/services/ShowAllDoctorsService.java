package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.dependency_injection.DIDependency;
import lv.javaguru.java2.hospital.doctor.core.requests.ShowAllDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;

import java.util.List;

@DIComponent
public class ShowAllDoctorsService {
    @DIDependency private DoctorDatabaseImpl database;

    public ShowAllDoctorsResponse execute(ShowAllDoctorsRequest request) {
        List<Doctor> doctors = database.showAllDoctors();
        return new ShowAllDoctorsResponse(doctors);
    }
}
