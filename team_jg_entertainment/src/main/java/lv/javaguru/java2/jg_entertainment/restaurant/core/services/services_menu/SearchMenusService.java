package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.OrderingMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.SearchMenusRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.SearchMenusResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsMenus.SearchMenusRequestValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;

public class SearchMenusService {

    private DatabaseMenu databaseMenu;
    private SearchMenusRequestValidator validator;

    public SearchMenusService(DatabaseMenu databaseMenu,
                              SearchMenusRequestValidator validator) {
        this.databaseMenu = databaseMenu;
        this.validator = validator;
    }

    public SearchMenusResponse execute(SearchMenusRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchMenusResponse(null, errors);
        }

        List<Menu> menus = search(request);
        menus = order(menus, request.getOrderingMenu());

        return new SearchMenusResponse(menus, null);
    }

    private List<Menu> order(List<Menu> menus, OrderingMenu orderingMenu){
       if (orderingMenu != null) {
           Comparator<Menu> comparator = orderingMenu.getOrderBy().equals("title")
                   ? Comparator.comparing(Menu::getTitle)
                   : Comparator.comparing(Menu::getDescription);
           if (orderingMenu.getOrderDirection().equals("DESCENDING")) {
               comparator = comparator.reversed();
           }
           return menus.stream().sorted(comparator).collect(Collectors.toList());
       } else {
           return menus;
       }
    }

        private List<Menu> search ( SearchMenusRequest request){
            List<Menu> menus = new ArrayList<>();
            if (request.isTitleProvided() && !request.isDescriptionProvided()) {
                menus = databaseMenu.findByTitle(request.getTitle());
            }
            if (!request.isTitleProvided() && request.isDescriptionProvided()) {
                menus = databaseMenu.findByDescription(request.getDescription());
            }
            if (request.isTitleProvided() && request.isDescriptionProvided()) {
                menus = databaseMenu.findByTitleAndDescription(request.getTitle(), request.getDescription());
            }

            return menus;
        }

    }
