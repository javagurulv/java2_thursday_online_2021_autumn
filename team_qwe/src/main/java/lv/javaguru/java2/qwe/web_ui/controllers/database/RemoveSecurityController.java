package lv.javaguru.java2.qwe.web_ui.controllers.database;

import lv.javaguru.java2.qwe.core.requests.data_requests.RemoveSecurityRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.RemoveSecurityResponse;
import lv.javaguru.java2.qwe.core.services.data_services.RemoveSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemoveSecurityController {

    @Autowired private RemoveSecurityService service;

    @GetMapping(value = "/database/removeSecurity")
    public String showRemoveSecurityPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RemoveSecurityRequest());
        return "database/removeSecurity";
    }

    @PostMapping("/database/removeSecurity")
    public String processRemoveSecurityRequest(@ModelAttribute(value = "request") RemoveSecurityRequest request, ModelMap modelMap) {
        System.out.println(request.getTicker());
        RemoveSecurityResponse response = service.execute(request);
        if (!response.isRemoved()) {
            modelMap.addAttribute("isRemoved", "No security with such ticker!");
            return "database/removeSecurity";
        } else {
            return "/dataMenu";
        }
    }

}