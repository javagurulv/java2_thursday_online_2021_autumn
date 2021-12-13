package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.rest;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/table")
public class TableRestController {

    @Autowired private GetTableService getTableService;
    @Autowired private AddTableService addTableService;
    @Autowired private UpdateTableService updateTableService;
    @Autowired private RemoveTableService removeTableService;
    @Autowired private GetAllTablesService showAllTablesService;
    @Autowired private SearchTableService searchTableService;

    @GetMapping(path = "/{id}",
            produces = "application/json")
    public GetTableResponse getTable(@PathVariable Long id) {
        GetTableRequest request = new GetTableRequest(id);
        return getTableService.execute(request);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddTableResponse addUser(@RequestBody AddTableRequest request) {
        return addTableService.execute(request);
    }

    @PutMapping(path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public UpdateTableResponse updateTable(@RequestBody UpdateTableRequest request) {
        return updateTableService.execute(request);
    }

    @DeleteMapping(path = "/{id}",
            produces = "application/json")
    public RemoveTableResponse removeTable(@PathVariable Long id) {
        return removeTableService.execute(new RemoveTableRequest(id));
    }

    @GetMapping(path = "/",
            produces = "application/json")
    public GetAllTablesResponse showAllTables() {
        GetAllTablesRequest request = new GetAllTablesRequest();
        return showAllTablesService.execute(request);
    }

    @PostMapping(path = "/search",
            consumes = "application/json",
            produces = "application/json")
    public SearchTableResponse searchTablePost(@RequestBody SearchTableRequest request) {
        return searchTableService.execute(request);
    }

    @GetMapping(path = "/search",
            produces = "application/json")
    public SearchTableResponse searchTableGet(@RequestParam String titleTable) {
        SearchTableRequest request = new SearchTableRequest(titleTable);
        return searchTableService.execute(request);
    }
}
