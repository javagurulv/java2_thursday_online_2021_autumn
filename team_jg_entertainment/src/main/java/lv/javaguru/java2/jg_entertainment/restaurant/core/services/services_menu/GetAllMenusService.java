package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.GetAllMenusRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.GetAllMenusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAllMenusService {

    @Autowired private MenuRepository menuRepository;

    public GetAllMenusResponse execute(GetAllMenusRequest request) {
        List<Menu> menus = menuRepository.getAllMenus();
        return new GetAllMenusResponse(menus);
    }

}