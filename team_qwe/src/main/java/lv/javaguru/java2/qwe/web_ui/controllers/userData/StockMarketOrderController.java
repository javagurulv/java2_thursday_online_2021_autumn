package lv.javaguru.java2.qwe.web_ui.controllers.userData;

import lv.javaguru.java2.qwe.core.API.API;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.TradeType;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByTickerOrNameRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.FindUserByNameRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.StockMarketOrderRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.StockMarketOrderResponse;
import lv.javaguru.java2.qwe.core.services.data_services.FindSecurityByTickerOrNameService;
import lv.javaguru.java2.qwe.core.services.user_services.FindUserByNameService;
import lv.javaguru.java2.qwe.core.services.user_services.StockMarketOrderService;
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
public class StockMarketOrderController {

    @Autowired private StockMarketOrderService service;
    @Autowired private FindUserByNameService findUserByNameService;
    @Autowired private FindSecurityByTickerOrNameService findSecurityByTickerOrNameService;
    @Autowired private UtilityMethods utils;
    @Autowired private UserData userData;
    @Autowired private API api;

    @GetMapping(value = "/userdata/stockMarketOrder")
    public String showStockMarketOrderPage(ModelMap modelMap) {
        modelMap.addAttribute("users", Arrays.stream(utils.convertToStringArray(userData))
                .collect(Collectors.toList()));
        modelMap.addAttribute("request", new AuxiliaryDomain());
        return "userdata/stockMarketOrder";
    }

    @PostMapping("/userdata/stockMarketOrder")
    public String processStockMarketOrderRequest(@ModelAttribute(value = "request") AuxiliaryDomain domain, ModelMap modelMap) {
        StockMarketOrderRequest request = new StockMarketOrderRequest();
        if (domain.getName() != null) {
            User user = findUserByNameService.execute(new FindUserByNameRequest(domain.getName())).getUser();
            request.setUser(user);
        }
        if (domain.getTicker() != null) {
            Security security = findSecurityByTickerOrNameService.execute(
                    new FindSecurityByTickerOrNameRequest(domain.getTicker())).getSecurity();
            double marketPrice = api.getQuote(domain.getTicker());
            request.setSecurity(security);
            request.setRealTimePrice(marketPrice);
        }
        if (domain.getQuantity() != null) {
            String quantity = (domain.getType().equals("BUY")) ? domain.getQuantity() : "-" + domain.getQuantity();
            request.setQuantity(quantity);
        }
        StockMarketOrderResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        }
        else if (response.getTicket().getTradeType().equals(TradeType.BUY)) {
            modelMap.addAttribute("buyTrade", "You bought " + response.getPosition().getAmount() + " of " +
                    response.getPosition().getSecurity().getTicker() + " at " + response.getPosition().getPurchasePrice());
        }
        else {
            modelMap.addAttribute("sellTrade", "You sold " + response.getPosition().getAmount() + " of " +
                    response.getPosition().getSecurity().getTicker() + " at " + response.getPosition().getPurchasePrice());
        }
        return "userdata/stockMarketOrder";
    }

}