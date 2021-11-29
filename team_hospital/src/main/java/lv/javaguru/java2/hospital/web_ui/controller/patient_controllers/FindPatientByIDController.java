package lv.javaguru.java2.hospital.web_ui.controller.patient_controllers;

import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.responses.DeletePatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.FindPatientByIDResponse;
import lv.javaguru.java2.hospital.patient.core.services.FindPatientByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindPatientByIDController {

    @Autowired private FindPatientByIdService findPatientByIdService;

    @GetMapping(value = "/findPatientByID")
    public String showFindByIDPatientPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new FindPatientByIdRequest());
        return "patient/findPatientByID";
    }

    @PostMapping("/findPatientByID")
    public String processFindByIDRequest(@ModelAttribute(value = "request") FindPatientByIdRequest request, ModelMap modelMap) {
        FindPatientByIDResponse response = findPatientByIdService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        }
        return "patient/findPatientByID";
    }
}
