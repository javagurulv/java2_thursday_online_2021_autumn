package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.EditReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.EditReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation.EditReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditReservationController {

    @Autowired private EditReservationService service;

    @GetMapping(value = "/editReservation")
    public String showEditReservationPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new EditReservationRequest());
        return "reservation/editReservation";
    }

    @PostMapping("/editReservation")
    public String processEdiReservationRequest(@ModelAttribute(value = "request") EditReservationRequest request, ModelMap modelMap) {
        EditReservationResponse response = service.execute(request);
        if (response.hasError()) {
            modelMap.addAttribute("errors", response.getErrorList());
        } else if (!response.hasError()) {
            modelMap.addAttribute("message", "Reservation was changed");
        }
        return "reservation/editReservation";
    }
}
