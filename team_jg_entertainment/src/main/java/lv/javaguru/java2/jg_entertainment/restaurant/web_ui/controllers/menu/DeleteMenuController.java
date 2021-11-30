package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.RemoveMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.RemoveTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.RemoveMenuResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.RemoveTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.RemoveMenuService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.RemoveTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteMenuController {

    @Autowired
    private RemoveMenuService service;

    @GetMapping(value = "/deleteMenu")
    public String showDeleteMenuPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RemoveMenuRequest());
        return "menu/deleteMenu";
    }

    @PostMapping("/deleteMenu")
    public String processDeleteMenuRequest(@ModelAttribute(value = "request") RemoveMenuRequest request, ModelMap modelMap) {
        RemoveMenuResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "menu/deleteMenu";
        } else {
            return "redirect:/";
        }
    }
}
