package lv.javaguru.java2.qwe.web_ui.controllers.userData;

import lv.javaguru.java2.qwe.core.requests.user_requests.FindUserByNameRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.FindUserByNameResponse;
import lv.javaguru.java2.qwe.core.services.user_services.FindUserByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindUserByIdOrNameController {

    @Autowired
    private FindUserByNameService service;

    @GetMapping(value = "/userdata/findUserByIdOrName")
    public String showFindUserByIdOrNamePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new FindUserByNameRequest());
        return "userdata/findUserByIdOrName";
    }

    @PostMapping("/userdata/findUserByIdOrName")
    public String processFindUserByIdOrNameRequest(
            @ModelAttribute(value = "request") FindUserByNameRequest request, ModelMap modelMap
    ) {
        FindUserByNameResponse response = service.execute(request);
        if (!response.hasErrors() && response.getUser() != null) {
            modelMap.addAttribute("user", response.getUser());
            return "userdata/findUserByIdOrName";
        } else if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "userdata/findUserByIdOrName";
        } else {
            modelMap.addAttribute("noResult", "There is no user with such id or name!");
            return "userdata/findUserByIdOrName";
        }
    }

}