package lv.javaguru.java2.hospital.web_ui.controller.patient_controllers;

import lv.javaguru.java2.hospital.patient.core.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.ShowAllPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.ShowAllPatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllPatientsController {

    @Autowired private ShowAllPatientsService showAllPatientsService;

    @GetMapping(value = "/showAllPatients")
    public String showAllBooks(ModelMap modelMap) {
        ShowAllPatientsResponse response = showAllPatientsService.execute(
                new ShowAllPatientsRequest()
        );
        modelMap.addAttribute("patients", response.getPatients());
        return "/patient/showAllPatients";
    }
}
