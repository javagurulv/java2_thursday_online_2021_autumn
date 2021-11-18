package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.RemoveMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.RemoveMenuResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_menus.RemoveMenuRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class RemoveMenuService {

    @Autowired private MenuRepository menuRepository;
    @Autowired private RemoveMenuRequestValidator validator;

    public RemoveMenuResponse execute(RemoveMenuRequest request) {
             List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveMenuResponse(errors);

        }
        boolean isMenuRemoved = menuRepository.deleteByNr(request.getMenuNumberToRemove());
        return new RemoveMenuResponse(isMenuRemoved);
    }
}