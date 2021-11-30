package lv.javaguru.java2.hospital.web_ui.controller.visit_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VisitActionsController {

    @GetMapping(value = "/visitActions")
    public String patientActions() {
        return "visit/visitActions";
    }
}
