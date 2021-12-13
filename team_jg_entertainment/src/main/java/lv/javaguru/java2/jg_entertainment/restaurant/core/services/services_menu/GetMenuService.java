package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.GetMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.GetMenuResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_menus.GetMenuValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetMenuService {

    @Autowired private MenuRepository menuRepository;
    @Autowired private GetMenuValidator validator;

    public GetMenuResponse execute(GetMenuRequest request) {
        List<CoreError> errorList = validator.validate(request);
        if(!errorList.isEmpty()) {
            return new GetMenuResponse(errorList);
        }
        return menuRepository.getById(request.getId())
                .map(GetMenuResponse::new)
                .orElseGet(() -> {
                    errorList.add(new CoreError("id", "was not found!"));
                    return new GetMenuResponse(errorList);
                });
    }
}
