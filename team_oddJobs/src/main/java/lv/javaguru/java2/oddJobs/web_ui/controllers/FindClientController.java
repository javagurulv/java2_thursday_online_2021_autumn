package lv.javaguru.java2.oddJobs.web_ui.controllers;


import lv.javaguru.java2.oddJobs.core.requests.find.FindClientsRequest;
import lv.javaguru.java2.oddJobs.core.responce.find.FindClientsResponse;
import lv.javaguru.java2.oddJobs.core.services.find.FindClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FindClientController {

    @Autowired private FindClientsService findClientsService;

    @GetMapping(value = "/findClient")
    public String showFindClient(ModelMap modelMap) {
        FindClientsResponse response = findClientsService.execute(
                new FindClientsRequest()
        );
        modelMap.addAttribute("Clients", response.getClients());
        return "/findClient";
    }
}
