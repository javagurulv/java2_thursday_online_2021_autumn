package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository.ReservationRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.ShowReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.ShowReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class ShowReservationServiceTest {

    @Mock private ReservationRepository databaseReservation;
    @InjectMocks private ShowReservationService service;

    @Test
    public void shouldShowListReservation(){
        List<Reservation> reservations = new ArrayList<>();
        User user = new User("name", "surname", "3723");
        user.setUserId(10L);
        Menu menu = new Menu("menu", "description", 10.00);
        menu.setNumber(30L);
        Table table = new Table("title", 1, 10.00);
        table.setId(20L);
        LocalDateTime dateTime = LocalDateTime.from((DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").parse("20-11-2021 19:00")));
        reservations.add(new Reservation(user, menu, table, dateTime));
        Mockito.when(databaseReservation.getAllReservations()).thenReturn(reservations);
        ShowReservationRequest request = new ShowReservationRequest();
        ShowReservationResponse response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getReservations().size(), 1);
        assertEquals(response.getReservations().get(0).getUser(), user);
        assertEquals(response.getReservations().get(0).getMenu(), menu);
        assertEquals(response.getReservations().get(0).getTable(), table);
        assertEquals(response.getReservations().get(0).getReservationDate(), dateTime);
    }
}