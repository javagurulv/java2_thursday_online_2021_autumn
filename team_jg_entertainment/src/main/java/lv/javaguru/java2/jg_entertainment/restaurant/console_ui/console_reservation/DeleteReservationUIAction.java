package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.DeleteReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.DeleteReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation.DeleteReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteReservationUIAction implements ReservationUIAction {

    @Autowired private DeleteReservationService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id reservation to delete");
        Long id = scanner.nextLong();

        DeleteReservationRequest request = new DeleteReservationRequest(id);
        DeleteReservationResponse response = service.execute(request);

        if (response.isTrue()) {
            System.out.println("Success!");
            System.out.println("Reservation with ID" + id + " was deleted");
        } else {
            System.out.println("Error! " + id + " id reservation, doesn't exist");
        }
    }
}
