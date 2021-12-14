package lv.javaguru.java2.oddJobs.web_ui.controllers;


import lv.javaguru.java2.oddJobs.core.requests.find.FindClientsRequest;
import lv.javaguru.java2.oddJobs.core.response.find.FindClientsResponse;
import lv.javaguru.java2.oddJobs.core.services.find.FindClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindClientController {

    @Autowired private FindClientService findClientsService;

    @GetMapping(value = "/findClient")
    public String showClientPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new FindClientsRequest());
        return "findClient";
    }

    @PostMapping("/findClient")
    public String processFindClientRequest(@ModelAttribute(value = "request") FindClientsRequest request, ModelMap modelMap) {
        FindClientsResponse response = findClientsService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());

        } else if (!response.hasErrors()) {
            modelMap.addAttribute("Clients", response.getClients());
        }
        return "findClient";

    }
}
