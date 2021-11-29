package lv.javaguru.java2.hospital.web_ui.controller.patient_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientActionsController {

    @GetMapping(value = "/patientActions")
    public String patientActions() {
        return "patient/patientActions";
    }
}
