package lv.javaguru.java2.qwe.web_ui.controllers.database;

import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByTickerOrNameRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.FindSecurityByTickerOrNameResponse;
import lv.javaguru.java2.qwe.core.services.data_services.FindSecurityByTickerOrNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindSecurityByTickerOrNameController {

    @Autowired private FindSecurityByTickerOrNameService service;

    @GetMapping(value = "/database/findSecurityByTickerOrName")
    public String showFindSecurityPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new FindSecurityByTickerOrNameRequest());
        return "database/findSecurityByTickerOrName";
    }

    @PostMapping("/database/findSecurityByTickerOrName")
    public String processFindSecurityRequest(
            @ModelAttribute(value = "request") FindSecurityByTickerOrNameRequest request, ModelMap modelMap
    ) {
        FindSecurityByTickerOrNameResponse response = service.execute(request);
        if (!response.hasErrors() && response.getSecurity() != null) {
            modelMap.addAttribute("security", response.getSecurity());
            return "database/findSecurityByTickerOrName";
        } else if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "database/findSecurityByTickerOrName";
        } else {
            modelMap.addAttribute("noResult", "There is no security with such name!");
            return "database/findSecurityByTickerOrName";
        }
    }

}