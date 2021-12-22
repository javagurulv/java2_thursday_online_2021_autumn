package lv.javaguru.java2.qwe.web_ui.controllers.userData;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioSummaryRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioSummaryResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserPortfolioSummaryService;
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
public class GetUserPortfolioSummaryController {

    @Autowired private GetUserPortfolioSummaryService service;
    @Autowired private UtilityMethods utils;

    @GetMapping(value = "/userdata/getUserPortfolioSummary")
    public String showUserPortfolioSummaryPage(ModelMap modelMap) {
        modelMap.addAttribute("users", Arrays.stream(utils.convertToStringArray(service.getUserData()))
                .collect(Collectors.toList()));
        modelMap.addAttribute("request", new GetUserPortfolioSummaryRequest());
        return "userdata/getUserPortfolioSummary";
    }

    @PostMapping("/userdata/getUserPortfolioSummary")
    public String processGetUserPortfolioSummaryRequest(
            @ModelAttribute(value = "request") GetUserPortfolioSummaryRequest request, ModelMap modelMap) {
        GetUserPortfolioSummaryResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("summary", response);
        }
        return "userdata/getUserPortfolioSummary";
    }

}