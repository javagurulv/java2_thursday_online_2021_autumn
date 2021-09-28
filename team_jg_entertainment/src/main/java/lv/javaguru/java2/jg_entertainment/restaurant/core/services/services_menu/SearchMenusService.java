package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import java.util.List;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseMenu;
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

        List<Menu> menus = null;
        if (request.isTitleProvided() && !request.isDescriptionProvided()) {
            menus = databaseMenu.findByTitle(request.getTitle());

        }
        		if (!request.isTitleProvided() && request.isDescriptionProvided()) {
                    menus = databaseMenu.findByDescription(request.getDescription());
        		}
        		if (request.isTitleProvided() && request.isDescriptionProvided()) {
        			menus = databaseMenu.findByTitleAndDescription(request.getTitle(), request.getDescription());
        		}

        		return new SearchMenusResponse(menus, null);
        	}

    }
