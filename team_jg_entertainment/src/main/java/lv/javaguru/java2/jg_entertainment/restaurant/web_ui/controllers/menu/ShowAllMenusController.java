package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.GetAllMenusRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.GetAllMenusResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.GetAllMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllMenusController {

    @Autowired
    private GetAllMenusService service;

    @GetMapping(value = "/showAllMenus")
    public String showAllMenus(ModelMap modelMap) {
        GetAllMenusResponse response = service.execute(new GetAllMenusRequest());
        modelMap.addAttribute("menus", response.getMenus());
        return "menu/showAllMenus";
    }
}
