package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.table;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.GetAllTablesRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.GetAllTablesResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.GetAllTablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ShowAllTablesController {

    @Autowired
    private GetAllTablesService service;

    @GetMapping(value = "/showAllTables")
    public String showAllTables(ModelMap modelMap) {
        GetAllTablesResponse response = service.execute(new GetAllTablesRequest());
        modelMap.addAttribute("tables", response.getTables());
        return "table/showAllTables";
    }
}
