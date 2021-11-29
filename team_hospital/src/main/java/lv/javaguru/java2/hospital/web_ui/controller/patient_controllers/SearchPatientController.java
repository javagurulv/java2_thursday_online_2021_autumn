package lv.javaguru.java2.hospital.web_ui.controller.patient_controllers;

import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.SearchPatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchPatientController {

    @Autowired private SearchPatientsService searchPatientsService;

    @GetMapping(value = "/searchPatient")
    public String showEditPatientPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchPatientsRequest());
        return "patient/searchPatient";
    }

    @PostMapping("/searchPatient")
    public String processEditPatientRequest(@ModelAttribute(value = "request") SearchPatientsRequest request, ModelMap modelMap) {
        SearchPatientsResponse response = searchPatientsService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        }
        modelMap.addAttribute("patients", response.getPatientList());
        return "patient/searchPatient";
    }
}
