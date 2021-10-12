package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.doctor.core.requests.DoctorOrdering;
import lv.javaguru.java2.hospital.doctor.core.requests.DoctorPaging;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.SearchDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.search_criteria.*;
import lv.javaguru.java2.hospital.doctor.core.services.validators.SearchDoctorsRequestValidator;
import lv.javaguru.java2.hospital.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
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
            if (doctorOrdering.getOrderBy().toUpperCase(Locale.ROOT).equals("NAME")) {
                comparator = Comparator.comparing(Doctor::getName);
            } else if (doctorOrdering.getOrderBy().toUpperCase(Locale.ROOT).equals("SURNAME")) {
                comparator = Comparator.comparing(Doctor::getSurname);
            } else {
                comparator = Comparator.comparing(Doctor::getSpeciality);
            }
            if (doctorOrdering.getOrderDirection().toUpperCase(Locale.ROOT).equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return doctors.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return doctors;
        }
    }


    private List<Doctor> search(SearchDoctorsRequest request) {
        List<Doctor> doctors = new ArrayList<>();

        DoctorsSearchCriteria[] doctorsSearchCriteria = getDoctorsSearchCriteria();

        for (DoctorsSearchCriteria processor : doctorsSearchCriteria) {
            if (processor.canProcess(request)) {
                doctors = processor.process(request);
                break;
            }
        }
        return doctors;
    }

    private DoctorsSearchCriteria[] getDoctorsSearchCriteria() {
        DoctorsSearchCriteria[] doctorsSearchCriteria = {
                new IdSearchCriteria(database),
                new NameAndSurnameAndSpecialitySearchCriteria(database),
                new NameAndSurnameSearchCriteria(database),
                new NameAndSpecialitySearchCriteria(database),
                new SurnameAndSpecialitySearchCriteria(database),
                new NameSearchCriteria(database),
                new SurnameSearchCriteria(database),
                new SpecialitySearchCriteria(database)};
        return doctorsSearchCriteria;
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
