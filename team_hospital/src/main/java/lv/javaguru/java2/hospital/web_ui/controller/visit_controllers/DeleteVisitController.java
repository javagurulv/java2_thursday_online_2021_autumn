package lv.javaguru.java2.hospital.web_ui.controller.visit_controllers;

import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.DeletePatientResponse;
import lv.javaguru.java2.hospital.visit.core.requests.DeleteVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.DeleteVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.DeleteVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteVisitController {

    @Autowired private DeleteVisitService deleteVisitService;

    @GetMapping(value = "/deleteVisit")
    public String showDeleteVisitPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteVisitRequest());
        return "visit/deleteVisit";
    }

    @PostMapping("/deleteVisit")
    public String processDeleteVisitRequest(@ModelAttribute(value = "request") DeleteVisitRequest request, ModelMap modelMap) {
        DeleteVisitResponse response = deleteVisitService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else if (!response.hasErrors()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Successfully deleted!");
        }
        return "visit/deleteVisit";
    }
}
