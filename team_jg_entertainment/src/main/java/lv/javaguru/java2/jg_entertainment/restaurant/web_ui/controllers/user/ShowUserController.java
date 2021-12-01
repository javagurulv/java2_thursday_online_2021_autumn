package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.user;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.ShowAllUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.ShowAllUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.ShowListUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowUserController {

    @Autowired private ShowListUsersService service;

    @GetMapping(value = "/showUsers")
    public String showUsers(ModelMap modelMap) {
        ShowAllUsersResponse response = service.execute(new ShowAllUsersRequest());
        modelMap.addAttribute("visitors", response.getNewUser());
        return "user/showUsers";
    }
}
