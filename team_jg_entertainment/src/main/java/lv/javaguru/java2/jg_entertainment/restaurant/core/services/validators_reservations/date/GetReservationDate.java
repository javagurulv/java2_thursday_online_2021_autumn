package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.date;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class GetReservationDate {
    public LocalDateTime getReservationDateFromString(String date) {
        return LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").parse(date));
    }

    //new part
    public String getStringDate(LocalDateTime date){
        return LocalDateTime.from(date).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
