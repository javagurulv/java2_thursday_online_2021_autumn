package lv.javaguru.java2.hospital.visit.core.services.search_criteria;

import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetVisitDate {

    public Date getVisitDateFromString(String date) {
        Date visitDate = null;
        try {
            visitDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return visitDate;
    }
}
