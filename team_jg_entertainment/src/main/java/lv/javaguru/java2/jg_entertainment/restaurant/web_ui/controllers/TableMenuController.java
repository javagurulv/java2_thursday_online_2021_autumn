package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TableMenuController {

    @GetMapping(value = "/tableMenu")
    public String index() {
        return "tableMenu";
    }
}
