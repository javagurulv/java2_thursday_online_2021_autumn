package lv.javaguru.java2.qwe.web_ui.controllers.database;

import lv.javaguru.java2.qwe.core.requests.data_requests.GetAllSecurityListRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.GetAllSecurityListResponse;
import lv.javaguru.java2.qwe.core.services.data_services.GetAllSecurityListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetAllSecurityListController {

    @Autowired private GetAllSecurityListService service;

    @GetMapping(value = "/getAllSecurityList")
    public String showAllSecurityList(ModelMap modelMap) {
        GetAllSecurityListResponse response = service.execute(
                new GetAllSecurityListRequest()
        );
        modelMap.addAttribute("securities", response.getList());
        return "/getAllSecurityList";
    }

}