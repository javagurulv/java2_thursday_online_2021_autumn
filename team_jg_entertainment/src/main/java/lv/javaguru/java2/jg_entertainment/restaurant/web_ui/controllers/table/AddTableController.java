package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.table;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.AddTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.AddTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.AddTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddTableController {

    @Autowired private AddTableService service;

    @GetMapping(value = "/addTable")
    public String showAddTablePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddTableRequest());
        return "table/addTable";
    }

    @PostMapping("/addTable")
    public String processAddTableRequest(@ModelAttribute(value = "request") AddTableRequest request, ModelMap modelMap) {
        AddTableResponse response = service.execute(request);
        if (response.hasError()) {
            modelMap.addAttribute("errors", response.getErrorsList());
            return "table/addTable";
        }
        if (!response.hasError()) {
            modelMap.addAttribute("message", "Table was added!");
            return "table/addTable";
        }
        return "redirect:/";
    }
}


