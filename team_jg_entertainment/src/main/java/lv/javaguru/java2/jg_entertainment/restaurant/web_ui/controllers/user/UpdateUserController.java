package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.user;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.UpdateUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.UpdateUserResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpdateUserController {

    @Autowired private UpdateUserService service;

    @GetMapping(value = "/updateUser")
    public String updateUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new UpdateUserRequest());
        return "user/updateUser";
    }

    @PostMapping("/updateUser")
    public String processUpdateUserRequest(@ModelAttribute(value = "request") UpdateUserRequest request, ModelMap modelMap) {
        UpdateUserResponse response = service.execute(request);
        if (response.hasError()) {
            modelMap.addAttribute("errors", response.getErrorsList());
            return "user/updateUser";
        } else {
            return "redirect:/";
        }
    }
}
