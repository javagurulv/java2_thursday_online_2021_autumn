package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.UpdateReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.UpdateReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation.UpdateReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpdateReservationController {

    @Autowired private UpdateReservationService service;

    @GetMapping(value = "/updateReservation")
    public String showEditReservationPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new UpdateReservationRequest());
        return "reservation/updateReservation";
    }

    @PostMapping("/updateReservation")
    public String processEdiReservationRequest(@ModelAttribute(value = "request") UpdateReservationRequest request, ModelMap modelMap) {
        UpdateReservationResponse response = service.execute(request);
        if (response.hasError()) {
            modelMap.addAttribute("errors", response.getErrorList());
        } else if (!response.hasError()) {
            modelMap.addAttribute("message", "Reservation was changed");
        }
        return "reservation/updateReservation";
    }
}
