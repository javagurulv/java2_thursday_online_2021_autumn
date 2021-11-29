package lv.javaguru.java2.hospital.web_ui.controller.patient_controllers;

import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.DeletePatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.DeletePatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeletePatientController {

    @Autowired
    private DeletePatientService deletePatientService;

    @GetMapping(value = "/deletePatient")
    public String showDeletePatientPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeletePatientRequest());
        return "patient/deletePatient";
    }

    @PostMapping("/deletePatient")
    public String processDeletePatientRequest(@ModelAttribute(value = "request") DeletePatientRequest request, ModelMap modelMap) {
        DeletePatientResponse response = deletePatientService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else if (!response.hasErrors()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Successfully deleted!");
        }
        return "patient/deletePatient";
    }
}
