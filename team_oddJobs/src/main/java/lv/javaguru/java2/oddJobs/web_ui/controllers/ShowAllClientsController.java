package lv.javaguru.java2.oddJobs.web_ui.controllers;

import lv.javaguru.java2.oddJobs.core.requests.get.GetAllClientsRequest;
import lv.javaguru.java2.oddJobs.core.response.get.GetAllClientsResponse;
import lv.javaguru.java2.oddJobs.core.services.get.GetAllClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllClientsController {

    @Autowired
    private GetAllClientsService getAllClientsService;

    @GetMapping(value = "/showAllClients")
    public String showAllClients(ModelMap modelMap) {
        GetAllClientsResponse response = getAllClientsService.execute(
                new GetAllClientsRequest()
        );
        modelMap.addAttribute("Clients", response.getClients());
        return "/showAllClients";
    }
}
