package lv.javaguru.java2.hospital.web_ui.controller.visit_controllers;

import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.SearchVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.search_visit_service.SearchVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchVisitController {

    @Autowired private SearchVisitService searchVisitService;

    @GetMapping(value = "/searchVisit")
    public String showSearchVisitPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchVisitRequest());
        return "visit/searchVisit";
    }

    @PostMapping("/searchVisit")
    public String processSearchVisitRequest(@ModelAttribute(value = "request") SearchVisitRequest request, ModelMap modelMap) {
        SearchVisitResponse response = searchVisitService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        }else if (!response.hasErrors()){
            modelMap.addAttribute("visits", response.getVisits());
        }
        return "visit/searchVisit";
    }
}