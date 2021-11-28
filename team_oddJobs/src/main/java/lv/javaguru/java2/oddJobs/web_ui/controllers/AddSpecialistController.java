package lv.javaguru.java2.oddJobs.web_ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lv.javaguru.java2.oddJobs.core.requests.add.AddSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.services.add.AddSpecialistService;
import lv.javaguru.java2.oddJobs.core.responce.add.AddSpecialistResponse;
@Controller
public class AddSpecialistController {
    @Autowired
    private AddSpecialistService addSpecialistService;

    @GetMapping(value = "/addSpecialist")
    public String showAddSpecialistPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddSpecialistRequest());
        return "addSpecialist";
    }

    @PostMapping("/addSpecialist")
    public String processAddSpecialistRequest(@ModelAttribute(value = "request") AddSpecialistRequest request,ModelMap modelMap) {
        AddSpecialistResponse response = addSpecialistService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addSpecialist";
        } else {
            return "redirect:/";
        }
    }
}
