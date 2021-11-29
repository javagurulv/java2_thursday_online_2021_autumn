package lv.javaguru.java2.hospital.web_ui.controller.patient_controllers;

import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddPatientController {

    @Autowired private AddPatientService addPatientService;

    @GetMapping(value = "/addPatientToList")
    public String showAddPatientPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddPatientRequest());
        return "patient/addPatientToList";
    }

    @PostMapping("/addPatientToList")
    public String processAddBookRequest(@ModelAttribute(value = "request") AddPatientRequest request, ModelMap modelMap) {
        AddPatientResponse response = addPatientService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else if (!response.hasErrors()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Successfully added!");
        }
        return "patient/addPatientToList";
    }
}