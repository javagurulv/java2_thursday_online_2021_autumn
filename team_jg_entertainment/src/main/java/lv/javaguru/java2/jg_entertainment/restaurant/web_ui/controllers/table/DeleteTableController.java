package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.table;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.RemoveTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.RemoveTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.RemoveTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteTableController {
    @Autowired
    private RemoveTableService service;

    @GetMapping(value = "/deleteTable")
    public String showDeleteTablePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RemoveTableRequest());
        return "table/deleteTable";
    }

    @PostMapping("/deleteTable")
    public String processDeleteTableRequest(@ModelAttribute(value = "request") RemoveTableRequest request, ModelMap modelMap) {
        RemoveTableResponse response = service.execute(request);
        if (response.hasError()) {
            modelMap.addAttribute("errors", response.getErrorsList());
            return "table/deleteTable";
        } else {
            return "redirect:/";
        }

    }
}
