package lv.javaguru.java2.qwe.web_ui.controllers.rest;

import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.domain.TradeTicket;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.requests.user_requests.FindUserByNameRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.StockMarketOrderRequest;
import lv.javaguru.java2.qwe.core.services.user_services.FindUserByNameService;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserPortfolioService;
import lv.javaguru.java2.qwe.core.services.user_services.StockMarketOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired private FindUserByNameService findUserService;
    @Autowired private GetUserPortfolioService getPortfolioService;
    @Autowired private StockMarketOrderService orderService;

    @GetMapping(path = "/{name}", produces = "application/json")
    public User getUser(@PathVariable String name) {
        return findUserService.execute(new FindUserByNameRequest(name)).getUser();
    }

    @GetMapping(path = "/portfolio/{name}", produces = "application/json")
    public List<Position> getPortfolio(@PathVariable String name) {
        return getPortfolioService.execute(new GetUserPortfolioRequest(name)).getPortfolio();
    }

    @PostMapping(path = "/order/",
            consumes = "application/json",
            produces = "application/json")
    public TradeTicket getTrade(@RequestBody StockMarketOrderRequest request) {
        return orderService.execute(request).getTicket();
    }

}