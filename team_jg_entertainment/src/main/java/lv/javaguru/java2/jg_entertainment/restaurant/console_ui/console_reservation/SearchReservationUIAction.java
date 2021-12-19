package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.ReservationOrdering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.ReservationPaging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.SearchReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.SearchReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation.SearchReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchReservationUIAction implements ReservationUIAction{

    @Autowired private SearchReservationService service;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter reservation ID: ");
        String reservationId = scanner.nextLine();
        System.out.println("Please, enter user ID: ");
        String userID = scanner.nextLine();
        System.out.println("Please, enter date when you want to reservation in format like-> yyyy-MM-dd HH:mm: ");
        String reservationDate = scanner.nextLine();

        System.out.println("Enter orderBy (reservationId||userID||reservationDate): ");
        String orderBy = scanner.nextLine();
        System.out.println("Enter orderDirection (ASCENDING || DESCENDING): ");
        String orderDirection = scanner.nextLine();
        ReservationOrdering ordering = new ReservationOrdering(orderBy, orderDirection);
        System.out.println("Enter pageNumber: ");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter pageSize: ");
        Integer pageSize = Integer.parseInt(scanner.nextLine());

        ReservationPaging paging = new ReservationPaging(pageNumber, pageSize);
        SearchReservationRequest request = new SearchReservationRequest(reservationId, userID, reservationDate, ordering, paging);
        SearchReservationResponse response = service.execute(request);

        if (response.hasError()) {
            response.getErrorList().forEach(coreError -> System.out.println("Errors: " +
                    coreError.getField() + " " + coreError.getMessageError()));
        } else {
            System.out.println(response.getReservations());
        }
    }
}
