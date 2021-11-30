package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.user;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.AddUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.AddUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.AddAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddUserController {

    @Autowired private AddAllUsersService service;

    @GetMapping(value = "/addUser")
    public String showAddUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddUserRequest());
        return "user/addUser";
    }

    @PostMapping("/addUser")
    public String processAddUserRequest(@ModelAttribute(value = "request") AddUserRequest request, ModelMap modelMap) {
        AddUsersResponse response = service.execute(request);
        if (response.hasError()) {
            modelMap.addAttribute("errors", response.getErrorsList());
            return "user/addUser";
        } else {
            return "redirect:/";
        }
    }
}
