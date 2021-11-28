package lv.javaguru.java2.oddJobs.web_ui.controllers;

import lv.javaguru.java2.oddJobs.core.requests.add.AddClientRequest;
import lv.javaguru.java2.oddJobs.core.responce.add.AddClientResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddClientController {
    @Autowired
    private AddClientService addClientService;

    @GetMapping(value = "/addClient")
    public String showAddClientPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddClientRequest());
        return "addClient";
    }

    @PostMapping("/addClient")
    public String processAddClientRequest(@ModelAttribute(value = "request") AddClientRequest request, ModelMap modelMap) {
        AddClientResponse response = addClientService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addClient";
        } else {
            return "redirect:/";
        }
    }
}
