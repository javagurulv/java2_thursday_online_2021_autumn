package lv.javaguru.java2.hospital.visit.core.services.date_converter;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class GetVisitDate {

    public LocalDateTime getVisitDateFromString(String date) {
        return LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").parse(date));
    }

    public String getVisitStringFromDate(LocalDateTime date) {
        return LocalDateTime.from(date).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
