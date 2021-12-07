package lv.javaguru.java2.qwe.web_ui.controllers.userData;

import lv.javaguru.java2.qwe.core.requests.user_requests.RemoveUserRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.RemoveUserResponse;
import lv.javaguru.java2.qwe.core.services.user_services.RemoveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemoveUserController {

    @Autowired
    private RemoveUserService service;

    @GetMapping(value = "/userdata/removeUser")
    public String showRemoveSecurityPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RemoveUserRequest());
        return "userdata/removeUser";
    }

    @PostMapping("/userdata/removeUser")
    public String processRemoveSecurityRequest(@ModelAttribute(value = "request") RemoveUserRequest request, ModelMap modelMap) {
        RemoveUserResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "userdata/removeUser";
        } else {
            return "redirect:/";
        }
    }

}