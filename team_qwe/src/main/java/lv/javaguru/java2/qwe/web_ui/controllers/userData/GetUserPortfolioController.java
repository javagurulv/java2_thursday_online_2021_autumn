package lv.javaguru.java2.qwe.web_ui.controllers.userData;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserPortfolioService;
import lv.javaguru.java2.qwe.core.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class GetUserPortfolioController {

    @Autowired private GetUserPortfolioService service;
    @Autowired private UtilityMethods utils;

    @GetMapping(value = "/userdata/getUserPortfolio")
    public String showUserPortfolioPage(ModelMap modelMap) {
        modelMap.addAttribute("users", Arrays.stream(utils.convertToStringArray(service.getUserData()))
                .collect(Collectors.toList()));
        modelMap.addAttribute("request", new GetUserPortfolioRequest());
        return "userdata/getUserPortfolio";
    }

    @PostMapping("/userdata/getUserPortfolio")
    public String processGetUserPortfolioRequest(@ModelAttribute(value = "request") GetUserPortfolioRequest request, ModelMap modelMap) {
        GetUserPortfolioResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("portfolio", response.getPortfolio());
            modelMap.addAttribute("cash", response.getUser().getCash());
        }
        return "userdata/getUserPortfolio";
    }

}