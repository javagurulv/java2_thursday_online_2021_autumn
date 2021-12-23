package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.DeleteReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.DeleteReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation.DeleteReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteReservationController {

    @Autowired private DeleteReservationService service;

    @GetMapping(value = "/deleteReservation")
    public String showDeleteReservationPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteReservationRequest());
        return "reservation/deleteReservation";
    }

    @PostMapping("/deleteReservation")
    public String processDeleteReservationRequest(@ModelAttribute(value = "request") DeleteReservationRequest request, ModelMap modelMap) {
        DeleteReservationResponse response = service.execute(request);
        if (response.hasError()) {
            modelMap.addAttribute("errors", response.getErrorList());
            return "reservation/deleteReservation";
        }
        if (!response.hasError()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Reservation was deleted!");
            return "reservation/deleteReservation";
        }
        return "redirect:/";
    }
}