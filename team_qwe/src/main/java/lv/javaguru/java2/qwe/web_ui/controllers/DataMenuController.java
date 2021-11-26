package lv.javaguru.java2.qwe.web_ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataMenuController {

    @GetMapping(value = "/dataMenu")
    public String index() {
        return "dataMenu";
    }

}