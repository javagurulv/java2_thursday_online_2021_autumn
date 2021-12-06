package lv.javaguru.java2.qwe.web_ui.controllers.userData;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserTradesRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserTradesResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserPortfolioService;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserTradesService;
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
public class GetUserTradesController {

    @Autowired private GetUserTradesService service;
    @Autowired private UtilityMethods utils;

    @GetMapping(value = "/userdata/getUserTrades")
    public String showGetUserTradesPage(ModelMap modelMap) {
        modelMap.addAttribute("users", Arrays.stream(utils.convertToStringArray(service.getUserData()))
                .collect(Collectors.toList()));
        modelMap.addAttribute("request", new GetUserTradesRequest());
        return "userdata/getUserTrades";
    }

    @PostMapping("/userdata/getUserTrades")
    public String processGetUserTradesRequest(@ModelAttribute(value = "request") GetUserTradesRequest request, ModelMap modelMap) {
        modelMap.addAttribute("users", Arrays.stream(utils.convertToStringArray(service.getUserData()))
                .collect(Collectors.toList()));
        GetUserTradesResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("trades", response.getTrades());
        }
        return "userdata/getUserTrades";
    }

}