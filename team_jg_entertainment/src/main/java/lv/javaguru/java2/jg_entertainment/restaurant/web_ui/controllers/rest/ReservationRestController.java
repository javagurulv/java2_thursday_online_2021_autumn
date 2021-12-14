package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.rest;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationRestController {

    @Autowired private AddReservationService addReservationService;
    @Autowired private DeleteReservationService deleteReservationService;
    @Autowired private ShowReservationService showReservationService;
    @Autowired private SearchReservationService searchReservationService;
    @Autowired private EditReservationService updateReservation;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddReservationResponse addReservation(@RequestBody AddReservationRequest request) {
        return addReservationService.execute(request);
    }

    @DeleteMapping(path = "{id}",
            produces = "application/json")
    public DeleteReservationResponse deleteReservation(@PathVariable Long id) {
        return deleteReservationService.execute(new DeleteReservationRequest(id));
    }

    @GetMapping(path = "/",
            produces = "application/json")
    public ShowReservationResponse showReservation() {
        return showReservationService.execute(new ShowReservationRequest());
    }

    @PostMapping(path = "/search",
            consumes = "application/json",
            produces = "application/json")
    public SearchReservationResponse searchReservationPost(@RequestBody SearchReservationRequest request) {
        return searchReservationService.execute(request);
    }

    @GetMapping(path = "/search",
            produces = "application/json")
    public SearchReservationResponse searchReservationPost(@RequestParam String reservationId) {
        return searchReservationService.execute(new SearchReservationRequest(reservationId));
    }

    @PutMapping(path = "{id}",
            consumes = "application/json",
            produces = "application/json")
    public EditReservationResponse updateReservation(@RequestBody EditReservationRequest request) {
        return updateReservation.execute(request);
    }
}
