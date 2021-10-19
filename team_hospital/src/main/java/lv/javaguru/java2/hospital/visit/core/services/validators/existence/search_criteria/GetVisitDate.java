package lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
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
