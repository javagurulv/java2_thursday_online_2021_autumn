package lv.javaguru.java2.oddJobs.web_ui.controllers;

import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveClientRequest;
import lv.javaguru.java2.oddJobs.core.response.remove.RemoveClientResponse;
import lv.javaguru.java2.oddJobs.core.services.remove.RemoveClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemoveClientController {

    @Autowired
    private RemoveClientService removeClientService;


    @GetMapping(value = "/removeClient")
    public String showRemoveClientPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RemoveClientRequest());
        return "removeClient";
    }

    @PostMapping("/removeClient")
    public String processRemoveClientRequest(@ModelAttribute(value = "request") RemoveClientRequest request, ModelMap modelMap) {
        RemoveClientResponse response = removeClientService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "removeClient";
        } else {
            return "redirect:/";
        }
    }
}
