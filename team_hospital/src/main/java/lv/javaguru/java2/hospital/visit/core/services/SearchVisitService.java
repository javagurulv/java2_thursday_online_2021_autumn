package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.VisitsDatabase;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.Ordering;
import lv.javaguru.java2.hospital.visit.core.requests.Paging;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.responses.SearchVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.validators.SearchVisitValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SearchVisitService {

    private VisitsDatabase visitDatabase;
    private SearchVisitValidator validator;

    public SearchVisitService(VisitsDatabase database, SearchVisitValidator validator) {
        this.visitDatabase = database;
        this.validator = validator;
    }

    public SearchVisitResponse execute(SearchVisitRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchVisitResponse(null, errors);
        }

        List<Visit> visits = search(request);
        visits = order(visits, request.getOrdering());
        visits = paging(visits, request.getPaging());

        return new SearchVisitResponse(visits, null);
    }

    private List<Visit> search(SearchVisitRequest request) {
        List<Visit> visits = new ArrayList<>();
        Date visitDate = getVisitDate(request);
        if (request.isVisitIdProvided()) {
            visits = visitDatabase.findByVisitId(request.getVisitId());
        } else if (request.isDoctorIdProvided() && request.isPatientIdProvided() && request.isDateProvided()) {
            visits = visitDatabase.findByDoctorIdAndPatientIdAndDate(request.getDoctorId(), request.getPatientId(), visitDate);
        } else if (request.isDoctorIdProvided() && request.isPatientIdProvided()) {
            visits = visitDatabase.findByDoctorIdAndPatientId(request.getDoctorId(), request.getPatientId());
        } else if (request.isDoctorIdProvided() && request.isDateProvided()) {
            visits = visitDatabase.findByDoctorIdAndDate(request.getDoctorId(), visitDate);
        } else if (request.isPatientIdProvided() && request.isDateProvided()) {
            visits = visitDatabase.findByPatientIdAndDate(request.getPatientId(), visitDate);
        } else if (request.isDoctorIdProvided()) {
            visits = visitDatabase.findByDoctorId(request.getDoctorId());
        } else if (request.isPatientIdProvided()) {
            visits = visitDatabase.findByPatientId(request.getPatientId());
        } else if (request.isDateProvided()) {
            visits = visitDatabase.findByDate(visitDate);
        }
        return visits;
    }

    private List<Visit> order(List<Visit> visits, Ordering ordering) {
        if (ordering != null) {
            Comparator<Visit> comparator = null;
            if (ordering.getOrderBy().equals("date")) {
                comparator = Comparator.comparing(Visit::getVisitDate);
            }
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return visits.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return visits;
        }
    }

    private List<Visit> paging(List<Visit> visits, Paging paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return visits.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return visits;
        }
    }

    private Date getVisitDate(SearchVisitRequest request) {
        Date visitDate = null;
        try {
            visitDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(request.getVisitDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return visitDate;
    }

}
