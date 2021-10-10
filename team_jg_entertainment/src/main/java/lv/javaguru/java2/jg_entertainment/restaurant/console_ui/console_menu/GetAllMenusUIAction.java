package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.GetAllMenusRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.GetAllMenusResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.GetAllMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllMenusUIAction implements UIAction {

    @Autowired private GetAllMenusService getAllMenusService;

//    public GetAllMenusUIAction(GetAllMenusService getAllMenusService) {
//        this.getAllMenusService = getAllMenusService;
//    }

    @Override
    public void execute() {
        System.out.println("Menu list: ");
        GetAllMenusRequest request = new GetAllMenusRequest();
        GetAllMenusResponse response = getAllMenusService.execute(request);
        response.getMenus().forEach(System.out::println);
        System.out.println("Menu list end.");
    }
}