package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.AddMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.AddMenuResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsMenus.AddMenuValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddMenuService {

    @Autowired private DatabaseMenu databaseMenu;
    @Autowired private AddMenuValidator validator;

//    public AddMenuService(DatabaseMenu databaseMenu,
//                          AddMenuValidator validator) {
//        this.databaseMenu = databaseMenu;
//        this.validator = validator;
//    }

    public AddMenuResponse execute(AddMenuRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddMenuResponse(errors);
        }

        Menu menu = new Menu(request.getTitle(), request.getDescription(), request.getPrice());
        databaseMenu.save(menu);

        return new AddMenuResponse(menu);
    }

}