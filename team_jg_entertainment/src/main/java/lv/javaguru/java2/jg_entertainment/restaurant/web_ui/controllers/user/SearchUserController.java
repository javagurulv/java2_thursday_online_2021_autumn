package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.user;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.SearchUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.SearchUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.SearchUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchUserController {

    @Autowired private SearchUsersService service;

    @GetMapping(value = "/searchUser")
    public String showSearchUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchUsersRequest());
        return "user/searchUser";
    }

    @PostMapping("/searchUser")
    public String processSearchUserRequest(@ModelAttribute(value = "request")
                                                       SearchUsersRequest request, ModelMap modelMap) {
        SearchUsersResponse response = service.execute(request);
        if (response.hasError()) {
            modelMap.addAttribute("errors", response.getErrorsList());
        } else if (!response.hasError()) {
            modelMap.addAttribute("visitors", response.getUsers());
        }
        return "user/searchUser";
    }
}
