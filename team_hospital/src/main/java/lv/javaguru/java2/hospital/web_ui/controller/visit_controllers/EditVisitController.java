package lv.javaguru.java2.hospital.web_ui.controller.visit_controllers;

import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.EditVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.EditVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditVisitController {

    @Autowired private EditVisitService editVisitService;

    @GetMapping(value = "/editVisit")
    public String showEditVisitPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new EditVisitRequest());
        return "visit/editVisit";
    }

    @PostMapping("/editVisit")
    public String processEditVisitRequest(@ModelAttribute(value = "request") EditVisitRequest request, ModelMap modelMap) {
        EditVisitResponse response = editVisitService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else if (!response.hasErrors()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Successfully edited!");
        }
        return "visit/editVisit";
    }
}
