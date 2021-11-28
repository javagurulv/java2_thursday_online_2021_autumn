package lv.javaguru.java2.oddJobs.web_ui.controllers;

import lv.javaguru.java2.oddJobs.core.requests.add.AddSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.remove.RemoveSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.services.remove.RemoveSpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemoveSpecialistController {
    @Autowired
    private RemoveSpecialistService removeSpecialistService;

    @GetMapping(value = "/removeSpecialist")
    public String showRemoveSpecialistPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RemoveSpecialistRequest());
        return "removeSpecialist";
    }

    @PostMapping("/removeSpecialist")
    public String processAddSpecialistRequest(@ModelAttribute(value = "request") RemoveSpecialistRequest request, ModelMap modelMap) {
        RemoveSpecialistResponse response = removeSpecialistService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "removeSpecialist";
        } else {
            return "redirect:/";
        }
    }
}
