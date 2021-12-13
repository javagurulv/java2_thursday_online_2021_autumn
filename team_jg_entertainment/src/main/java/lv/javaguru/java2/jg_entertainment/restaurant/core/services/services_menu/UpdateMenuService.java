package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.UpdateMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.UpdateMenuResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_menus.UpdateMenuRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UpdateMenuService {

    @Autowired private MenuRepository menuRepository;
    @Autowired private UpdateMenuRequestValidator validator;

    public UpdateMenuResponse execute(UpdateMenuRequest request) {
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()){
            return new UpdateMenuResponse(errors);
        }
        return menuRepository.getById(request.getMenuId())
                .map(menu -> {
                    menu.setTitle(request.getNewTitle());
                    menu.setDescription(request.getNewDescription());
                    menu.setPrice(request.getNewPrice());
                    return new UpdateMenuResponse(menu);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("menuId", "was not found"));
                    return new UpdateMenuResponse(errors);
                });
    }
}
