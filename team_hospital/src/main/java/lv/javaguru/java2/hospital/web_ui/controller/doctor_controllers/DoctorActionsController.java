package lv.javaguru.java2.hospital.web_ui.controller.doctor_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorActionsController {

    @GetMapping(value = "/doctorActions")
    public String showDoctorMenu(ModelMap modelMap) {
        return "doctor/doctorActions";
    }
}
