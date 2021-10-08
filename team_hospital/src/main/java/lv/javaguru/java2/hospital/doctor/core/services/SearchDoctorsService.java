package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.doctor.core.requests.DoctorOrdering;
import lv.javaguru.java2.hospital.doctor.core.requests.DoctorPaging;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.SearchDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.SearchDoctorsRequestValidator;
import lv.javaguru.java2.hospital.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchDoctorsService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired private DoctorDatabase database;
    @Autowired private SearchDoctorsRequestValidator validator;

    public SearchDoctorsResponse execute(SearchDoctorsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchDoctorsResponse(null, errors);
        }

        List<Doctor> doctors = search(request);
        doctors = order(doctors, request.getOrdering());
        doctors = paging(doctors, request.getPaging());

        return new SearchDoctorsResponse(doctors, null);
    }

    private List<Doctor> order(List<Doctor> doctors, DoctorOrdering doctorOrdering) {
        if (orderingEnabled && doctorOrdering != null) {
            Comparator<Doctor> comparator;
            if (doctorOrdering.getOrderBy().equals("name")) {
                comparator = Comparator.comparing(Doctor::getName);
            } else if (doctorOrdering.getOrderBy().equals("surname")) {
                comparator = Comparator.comparing(Doctor::getSurname);
            } else {
                comparator = Comparator.comparing(Doctor::getSpeciality);
            }
            if (doctorOrdering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return doctors.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return doctors;
        }
    }


    private List<Doctor> search(SearchDoctorsRequest request) {
        List<Doctor> doctors = new ArrayList<>();
        if (request.isIdProvided()) {
            doctors = database.findById(request.getId());
        } else if (request.isNameProvided() && request.isSurnameProvided() && request.isSpecialityProvided()) {
            doctors = database.findByNameAndSurnameAndSpeciality(request.getName(), request.getSurname(), request.getSpeciality());
        } else if (request.isNameProvided() && request.isSurnameProvided()) {
            doctors = database.findByNameAndSurname(request.getName(), request.getSurname());
        } else if (request.isNameProvided() && request.isSpecialityProvided()) {
            doctors = database.findByNameAndSpeciality(request.getName(), request.getSpeciality());
        } else if (request.isSurnameProvided() && request.isSpecialityProvided()) {
            doctors = database.findBySurnameAndSpeciality(request.getSurname(), request.getSpeciality());
        } else if (request.isNameProvided()) {
            doctors = database.findByName(request.getName());
        } else if (request.isSurnameProvided()) {
            doctors = database.findBySurname(request.getSurname());
        } else if (request.isSpecialityProvided()) {
            doctors = database.findBySpeciality(request.getSpeciality());
        }
        return doctors;
    }

    private List<Doctor> paging(List<Doctor> doctors, DoctorPaging doctorPaging) {
        if (pagingEnabled && doctorPaging != null) {
            int skip = (doctorPaging.getPageNumber() - 1) * doctorPaging.getPageSize();
            return doctors.stream()
                    .skip(skip)
                    .limit(doctorPaging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return doctors;
        }
    }
}
