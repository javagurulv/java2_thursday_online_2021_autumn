package lv.javaguru.java2.qwe.web_ui.controllers.database;

import lv.javaguru.java2.qwe.core.requests.data_requests.*;
import lv.javaguru.java2.qwe.core.responses.data_responses.FilterStocksByMultipleParametersResponse;
import lv.javaguru.java2.qwe.core.services.data_services.FilterStocksByMultipleParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FilterController {

    @Autowired
    private FilterStocksByMultipleParametersService service;


    @GetMapping(value = "/filter")
    public String showFilterPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DomainTest());
        return "filter";
    }

    @PostMapping("/filter")
    public String processFilterRequest(@ModelAttribute(value = "request") DomainTest domainTest, ModelMap modelMap) {
        if (domainTest.getIndustry() != null) {
            modelMap.addAttribute("industry",
                    new FilterStocksByIndustryRequest(
                            domainTest.getIndustryTarget())
            );
        }
        if (domainTest.getMarketPrice() != null) {
            modelMap.addAttribute("marketPrice", new FilterStocksByAnyDoubleParameterRequest(
                            "market_price",
                            domainTest.getMarketPriceOperator(),
                            domainTest.getMarketPriceTarget())
            );
        }
        if (domainTest.getDividends() != null) {
            modelMap.addAttribute("dividends", new FilterStocksByAnyDoubleParameterRequest(
                    "dividend_yield",
                    domainTest.getDividendsOperator(),
                    domainTest.getDividendsTarget())
            );
        }
        if (domainTest.getRiskWeight() != null) {
            modelMap.addAttribute("riskWeight", new FilterStocksByAnyDoubleParameterRequest(
                    "risk_weight",
                    domainTest.getRiskWeightOperator(),
                    domainTest.getRiskWeightTarget())
            );
        }
        if (domainTest.getMarketPriceTarget() != null) {
            List<CoreRequest> requestList = modelMap.entrySet().stream()
                    .filter(entry -> entry.getKey().equals("industry") || entry.getKey().equals("marketPrice")
                            || entry.getKey().equals("dividends") || entry.getKey().equals("riskWeight"))
                    .map(entry -> {
                        if (entry.getKey().equals("industry")) {
                            return (FilterStocksByIndustryRequest) entry.getValue();
                        }
                        if (entry.getKey().equals("marketPrice")) {
                            return (FilterStocksByAnyDoubleParameterRequest) entry.getValue();
                        }
                        if (entry.getKey().equals("dividends")) {
                            return (FilterStocksByAnyDoubleParameterRequest) entry.getValue();
                        }
                        return (FilterStocksByAnyDoubleParameterRequest) entry.getValue();
                    })
                    .collect(Collectors.toList());

            FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(requestList);
            FilterStocksByMultipleParametersResponse response = service.execute(request);

            modelMap.addAttribute("filtered", response.getList());
            return "filter";
        }
        return "filter";
    }

}