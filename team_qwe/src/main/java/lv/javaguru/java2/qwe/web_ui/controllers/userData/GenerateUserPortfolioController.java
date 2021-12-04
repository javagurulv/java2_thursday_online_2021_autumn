package lv.javaguru.java2.qwe.web_ui.controllers.userData;

import lv.javaguru.java2.qwe.core.requests.user_requests.GenerateUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GenerateUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GenerateUserPortfolioService;
import lv.javaguru.java2.qwe.core.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class GenerateUserPortfolioController {

    @Autowired
    private GenerateUserPortfolioService service;
    @Autowired private UtilityMethods utils;

    @GetMapping(value = "/userdata/generateUserPortfolio")
    public String showGenerateUserPortfolioPage(ModelMap modelMap) {
        modelMap.addAttribute("users", Arrays.stream(utils.convertToStringArray(service.getUserData()))
                .collect(Collectors.toList()));
        modelMap.addAttribute("request", new GenerateUserPortfolioRequest());
        return "userdata/generateUserPortfolio";
    }

    @PostMapping("/userdata/generateUserPortfolio")
    public String processGenerateUserPortfolioRequest(@ModelAttribute(value = "request") GenerateUserPortfolioRequest request, ModelMap modelMap) {
        GenerateUserPortfolioResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("portfolio", response.getPortfolio());
            modelMap.addAttribute("cash", response.getUser().getCash());
        }
        return "userdata/generateUserPortfolio";
    }

}