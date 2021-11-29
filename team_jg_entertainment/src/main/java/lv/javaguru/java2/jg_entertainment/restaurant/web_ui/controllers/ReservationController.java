package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {

    @GetMapping(value = "/reservationMenu")
    public String index() {
        return "reservationMenu";
    }
}
