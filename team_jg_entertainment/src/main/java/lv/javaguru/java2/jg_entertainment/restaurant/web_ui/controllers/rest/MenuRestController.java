package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.rest;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuRestController {

    @Autowired private GetMenuService getMenuService;
    @Autowired private AddMenuService addMenuService;
    @Autowired private UpdateMenuService updateMenuService;
    @Autowired private GetAllMenusService showAllMenusService;
    @Autowired private SearchMenusService searchMenusService;

    @GetMapping(path = "/{id}",
            produces = "application/json")
    public GetMenuResponse getMenu(@PathVariable Long id) {
        GetMenuRequest request = new GetMenuRequest(id);
        return getMenuService.execute(request);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddMenuResponse addMenu(@RequestBody AddMenuRequest request) {
        return addMenuService.execute(request);
    }

    @PutMapping(path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public UpdateMenuResponse updateMenu(@RequestBody UpdateMenuRequest request) {
        return updateMenuService.execute(request);
    }

    @GetMapping(path = "/",
            produces = "application/json")
    public GetAllMenusResponse showAllMenus(){
        GetAllMenusRequest request = new GetAllMenusRequest();
        return showAllMenusService.execute(request);
    }

    @PostMapping(path = "/searchMenu",
            consumes = "application/json",
            produces = "application/json")
    public SearchMenusResponse searchMenusPost(@RequestBody SearchMenusRequest request) {
        return searchMenusService.execute(request);
    }

    @GetMapping(path = "/searchMenu",
            produces = "application/json")
    public SearchMenusResponse searchMenusGet(@RequestParam String title,
                                              @RequestParam String description) {
        SearchMenusRequest request = new SearchMenusRequest(title, description);
        return searchMenusService.execute(request);
    }
}
