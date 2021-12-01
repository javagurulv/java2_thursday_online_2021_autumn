package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.SearchMenusRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.SearchMenusResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.SearchMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchMenuController {

    @Autowired private SearchMenusService service;

    @GetMapping(value = "/searchMenu")
    public String showSearchUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchMenusRequest());
        return "menu/searchMenu";
    }

    @PostMapping("/searchMenu")
    public String processSearchUserRequest(@ModelAttribute(value = "request")
                                                       SearchMenusRequest request, ModelMap modelMap) {
        SearchMenusResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else if (!response.hasErrors()) {
            modelMap.addAttribute("menus", response.getMenus());
        }
        return "menu/searchMenu";
    }
}
