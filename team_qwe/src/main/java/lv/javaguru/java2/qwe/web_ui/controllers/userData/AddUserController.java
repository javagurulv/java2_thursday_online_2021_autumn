package lv.javaguru.java2.qwe.web_ui.controllers.userData;

import lv.javaguru.java2.qwe.core.requests.user_requests.AddUserRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.AddUserResponse;
import lv.javaguru.java2.qwe.core.services.user_services.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddUserController {

    @Autowired private AddUserService service;

    @GetMapping(value = "/userdata/addUser")
    public String showAddStockPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddUserRequest());
        return "userdata/addUser";
    }

    @PostMapping("/userdata/addUser")
    public String processAddUserRequest(@ModelAttribute(value = "request") AddUserRequest request, ModelMap modelMap) {
        AddUserResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "userdata/addUser";
        } else {
            return "redirect:/";
        }
    }

}