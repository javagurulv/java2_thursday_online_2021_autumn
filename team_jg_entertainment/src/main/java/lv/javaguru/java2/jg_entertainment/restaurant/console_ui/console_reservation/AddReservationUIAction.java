package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.AddReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.AddReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation.AddReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddReservationUIAction implements ReservationUIAction {

    @Autowired
    private AddReservationService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter visitors name: ");
        String visitorID = scanner.nextLine();

        System.out.println("Please, enter title menu: ");
        String menuID = scanner.next();

        System.out.println("Please, enter title table: ");
        String tableID = scanner.next();

        System.out.println("Please, enter date when you want to reservation (dd/MM/yyyy):");
        String reservationDate = scanner.next();

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
                    + "visitor name-> " + response.getReservation().getVisitor()
                    + ", menu title-> " + response.getReservation().getMenu()
                    + ", table-> " + response.getReservation().getTable()
                    + ", date-> " + response.getReservation().getReservationDate()
                    + ", reservation id-> " + response.getReservation().getIdReservation()
            );
        }
    }
}
