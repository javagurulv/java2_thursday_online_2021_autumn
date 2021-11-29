package lv.javaguru.java2.hospital.web_ui.controller.patient_controllers;

import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.EditPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditPatientController {

    @Autowired private EditPatientService editPatientService;

    @GetMapping(value = "/editPatient")
    public String showEditPatientPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new EditPatientRequest());
        return "patient/editPatient";
    }

    @PostMapping("/editPatient")
    public String processEditPatientRequest(@ModelAttribute(value = "request") EditPatientRequest request, ModelMap modelMap) {
        EditPatientResponse response = editPatientService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else if (!response.hasErrors()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Successfully edited!");
        }
        return "patient/editPatient";
    }
}
