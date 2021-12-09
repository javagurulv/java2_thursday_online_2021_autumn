package lv.javaguru.java2.hospital.web_ui.controller.prescription_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrescriptionActionsController {

    @GetMapping(value = "/prescriptionActions")
    public String showPrescriptionMenu(ModelMap modelMap) {
        return "prescription/prescriptionActions";
    }
}
