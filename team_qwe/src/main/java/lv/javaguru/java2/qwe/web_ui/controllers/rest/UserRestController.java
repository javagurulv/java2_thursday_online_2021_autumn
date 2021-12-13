package lv.javaguru.java2.qwe.web_ui.controllers.rest;

import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.requests.user_requests.FindUserByNameRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.services.user_services.FindUserByNameService;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired private FindUserByNameService findUserService;
    @Autowired private GetUserPortfolioService getPortfolioService;

    @GetMapping(path = "/{name}", produces = "application/json")
    public User getUser(@PathVariable String name) {
        return findUserService.execute(new FindUserByNameRequest(name)).getUser();
    }

    @GetMapping(path = "/portfolio/{name}", produces = "application/json")
    public List<Position> getPortfolio(@PathVariable String name) {
        return getPortfolioService.execute(new GetUserPortfolioRequest(name)).getPortfolio();
    }

}