package lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class GetVisitDate {

    public LocalDateTime getVisitDateFromString(String date) {
        return LocalDateTime.from(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").parse(date));
    }
}
