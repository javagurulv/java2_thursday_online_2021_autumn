package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.AddMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.AddMenuResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.AddMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddMenuController {

    @Autowired private AddMenuService service;

    @GetMapping(value = "/addMenu")
    public String showAddTablePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddMenuRequest());
        return "menu/addMenu";
    }

    @PostMapping("/addMenu")
    public String processAddMenuRequest(@ModelAttribute(value = "request") AddMenuRequest request, ModelMap modelMap) {
        AddMenuResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "menu/addMenu";
        }
        if (!response.hasErrors()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Menu was added!");
            return "menu/addMenu";
        }
        return "redirect:/";
    }
}
