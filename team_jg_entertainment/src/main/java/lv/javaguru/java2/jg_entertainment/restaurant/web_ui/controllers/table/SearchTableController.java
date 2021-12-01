package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.table;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.SearchTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.SearchTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.SearchTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchTableController {

    @Autowired private SearchTableService service;

    @GetMapping(value = "/searchTable")
    public String showSearchUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchTableRequest());
        return "table/searchTable";
    }

    @PostMapping("/searchTable")
    public String processSearchUserRequest(@ModelAttribute(value = "request")
                                                       SearchTableRequest request, ModelMap modelMap) {
        SearchTableResponse response = service.execute(request);
        if (response.hasError()) {
            modelMap.addAttribute("errors", response.getErrorsList());
        } else if (!response.hasError()) {
            modelMap.addAttribute("tables", response.getTables());
        }
        return "table/searchTable";
    }
}
