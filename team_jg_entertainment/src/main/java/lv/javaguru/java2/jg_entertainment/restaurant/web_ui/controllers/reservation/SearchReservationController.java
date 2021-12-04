package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.SearchReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.SearchReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation.SearchReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchReservationController {

    @Autowired private SearchReservationService service;

    @GetMapping(value = "/searchReservation")
    public String showSearchReservationPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchReservationRequest());
        return "reservation/searchReservation";
    }

    @PostMapping("/searchReservation")
    public String processSearchReservationRequest(@ModelAttribute(value = "request")
                                                       SearchReservationRequest request, ModelMap modelMap) {
        SearchReservationResponse response = service.execute(request);
        if (response.hasError()) {
            modelMap.addAttribute("errors", response.getErrorList());
        } else if (!response.hasError()) {
            modelMap.addAttribute("reservations", response.getReservations());
        }
        return "reservation/searchReservation";
    }
}
//}
//        return "redirect:/";
//    }