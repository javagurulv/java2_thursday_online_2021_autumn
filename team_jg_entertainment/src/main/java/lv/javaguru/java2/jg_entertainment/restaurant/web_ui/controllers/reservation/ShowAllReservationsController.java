package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.ShowReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.ShowReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation.ShowReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllReservationsController {

    @Autowired private ShowReservationService service;

    @GetMapping(value = "/showAllReservations")
    public String showAllReservations(ModelMap modelMap) {
        ShowReservationResponse response = service.execute(new ShowReservationRequest());
        modelMap.addAttribute("reservations", response.getReservations());
        return "reservation/showAllReservations";
    }
}
