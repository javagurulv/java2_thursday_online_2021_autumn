package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.AddReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.AddReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation.AddReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddReservationUIAction implements ReservationUIAction {

    @Autowired private AddReservationService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter user ID: ");
        String visitorID = scanner.nextLine();

        System.out.println("Please, enter menu ID: ");
        String menuID = scanner.nextLine();

        System.out.println("Please, enter title ID: ");
        String tableID = scanner.nextLine();

        System.out.println("Please, enter date when you want to reservation in format like yyyy-MM-dd HH:mm HH:mm: ");
        String reservationDate = scanner.nextLine();

        AddReservationRequest request = new AddReservationRequest(visitorID, menuID, tableID, reservationDate);
        AddReservationResponse response = service.execute(request);

        if (response.hasError()) {
            response.getErrorList().forEach(coreError ->
                    System.out.println("Errors: " + coreError.getField() + " "
                            + coreError.getMessageError()));
        } else {
            System.out.println("Successful reservation!");
            System.out.println();
            System.out.println("Info reservation: "
                    + " user info-> " + response.getReservation().getVisitor()
                    + ", menu info-> " + response.getReservation().getMenu()
                    + ", table-> " + response.getReservation().getTable());
            System.out.println("Date reservation-> " + response.getReservation().getReservationDate()
                    + ", inform reservation id-> " + response.getReservation().getIdReservation());
        }
    }
}
