package lv.javaguru.java2.hospital.web_ui.controller.visit_controllers;

import lv.javaguru.java2.hospital.patient.core.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.ShowAllPatientsResponse;
import lv.javaguru.java2.hospital.visit.core.requests.ShowAllVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.ShowAllVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.ShowAllVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllVisitsController {

    @Autowired private ShowAllVisitService showAllVisitService;

    @GetMapping(value = "/showAllVisits")
    public String showAllVisits(ModelMap modelMap) {
        ShowAllVisitResponse response = showAllVisitService.execute(
                new ShowAllVisitRequest()
        );
        modelMap.addAttribute("visits", response.getPatientVisits());
        return "/visit/showAllVisits";
    }
}
