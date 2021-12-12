package lv.javaguru.java2.qwe.web_ui.controllers.rest;

import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.requests.user_requests.FindUserByNameRequest;
import lv.javaguru.java2.qwe.core.services.user_services.FindUserByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired private FindUserByNameService findUserService;

    @GetMapping(path = "/{name}", produces = "application/json")
    public User getUser(@PathVariable String name) {
        return findUserService.execute(new FindUserByNameRequest(name)).getUser();
    }

}