package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.user;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.DeleteUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.DeleteUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.DeleteUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteUserController {

    @Autowired private DeleteUsersService service;

    @GetMapping(value = "/deleteUser")
    public String showDeleteUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteUserRequest());
        return "user/deleteUser";
    }

    @PostMapping("/deleteUser")
    public String processDeleteUserRequest(@ModelAttribute(value = "request") DeleteUserRequest request, ModelMap modelMap) {
        DeleteUsersResponse response = service.execute(request);
        if (response.hasError()) {
            modelMap.addAttribute("errors", response.getErrorsList());
            return "user/deleteUser";
        } else {
            return "redirect:/";
        }
    }
}
