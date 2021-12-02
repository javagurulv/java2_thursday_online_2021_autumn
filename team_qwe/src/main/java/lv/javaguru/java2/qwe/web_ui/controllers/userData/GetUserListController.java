package lv.javaguru.java2.qwe.web_ui.controllers.userData;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetAllUserListRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetAllUserListResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetAllUserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetUserListController {

    @Autowired
    private GetAllUserListService service;

    @GetMapping(value = "/userdata/getUserList")
    public String showAllUserList(ModelMap modelMap) {
        GetAllUserListResponse response = service.execute(
                new GetAllUserListRequest()
        );
        modelMap.addAttribute("users", response.getList());
        return "/userdata/getUserList";
    }

}