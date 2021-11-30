package lv.javaguru.java2.hospital.web_ui.controller.visit_controllers;

import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.AddVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddVisitController {

    @Autowired private AddVisitService addVisitService;

    @GetMapping(value = "/addVisit")
    public String showAddVisitPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddVisitRequest());
        return "visit/addVisit";
    }

    @PostMapping("/addVisit")
    public String processAddVisitRequest(@ModelAttribute(value = "request") AddVisitRequest request, ModelMap modelMap) {
        AddVisitResponse response = addVisitService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else if (!response.hasErrors()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Successfully added!");
        }
        return "visit/addVisit";
    }
}
