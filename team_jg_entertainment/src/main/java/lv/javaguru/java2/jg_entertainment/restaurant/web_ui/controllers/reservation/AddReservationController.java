package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.AddReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.AddReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation.AddReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddReservationController {

    @Autowired private AddReservationService service;

    @GetMapping(value = "/addReservation")
    public String showAddUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddReservationRequest());
        return "reservation/addReservation";
    }
    @PostMapping("/addReservation")
    public String processAddUserRequest(@ModelAttribute(value = "request")
                                                    AddReservationRequest request, ModelMap modelMap) {
        AddReservationResponse response = service.execute(request);
        if (response.hasError()) {
            modelMap.addAttribute("errors", response.getErrorList());
            return "reservation/addReservation";
        } if (!response.hasError()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Reservation was added!");
            return "reservation/addReservation";
        }
            return "redirect:/";
    }
}
