package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.UpdateMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.UpdateMenuResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.UpdateMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpdateMenuController {

    @Autowired private UpdateMenuService service;

    @GetMapping(value = "/updateMenu")
    public String updateMenuPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new UpdateMenuRequest());
        return "menu/updateMenu";
    }

    @PostMapping("/updateMenu")
    public String processUpdateMenuRequest(@ModelAttribute(value = "request") UpdateMenuRequest request, ModelMap modelMap) {
        UpdateMenuResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "menu/updateMenu";
        } else {
            return "redirect:/";
        }
    }
}
