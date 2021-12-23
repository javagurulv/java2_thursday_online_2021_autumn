package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.table;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.UpdateTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.UpdateTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.UpdateTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpdateTableController {

    @Autowired private UpdateTableService service;

    @GetMapping(value = "/updateTable")
    public String updateTablePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new UpdateTableRequest());
        return "table/updateTable";
    }

    @PostMapping("/updateTable")
    public String processUpdateTableRequest(@ModelAttribute(value = "request") UpdateTableRequest request, ModelMap modelMap) {
        UpdateTableResponse response = service.execute(request);
        if (response.hasError()) {
            modelMap.addAttribute("errors", response.getErrorsList());
            return "table/updateTable";
        }
        if (!response.hasError()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Reservation was updated!");
            return "table/updateTable";
        }
        return "redirect:/";
    }
}
