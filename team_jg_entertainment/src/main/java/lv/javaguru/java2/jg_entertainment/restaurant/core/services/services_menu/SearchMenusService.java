package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.OrderingMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.PagingMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.SearchMenusRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.SearchMenusResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_menus.SearchMenusRequestValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class SearchMenusService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired private MenuRepository menuRepository;
    @Autowired private SearchMenusRequestValidator validator;

    public SearchMenusResponse execute(SearchMenusRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchMenusResponse(null, errors);
        }

        List<Menu> menus = search(request);
        menus = order(menus, request.getOrderingMenu());
        menus = paging(menus, request.getPagingMenu());
        return new SearchMenusResponse(menus, null);
    }

    private List<Menu> order(List<Menu> menus, OrderingMenu orderingMenu) {
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

    private List<Menu> search(SearchMenusRequest request) {
        List<Menu> menus = new ArrayList<>();
        if (request.isTitleProvided() && !request.isDescriptionProvided()) {
            menus = menuRepository.findByTitle(request.getTitle());
        }
        if (!request.isTitleProvided() && request.isDescriptionProvided()) {
            menus = menuRepository.findByDescription(request.getDescription());
        }
        if (request.isTitleProvided() && request.isDescriptionProvided()) {
            menus = menuRepository.findByTitleAndDescription(request.getTitle(), request.getDescription());
        }
        if (request.isIDProvided() && !request.isTitleProvided() && !request.isDescriptionProvided()) {
            menus = menuRepository.findById(request.getIdNumberMenu());
        }
        return menus;
    }

    private List<Menu> paging(List<Menu> menus, PagingMenu pagingMenu) {
        if (pagingMenu != null) {
            int skip = (pagingMenu.getPageNumber() - 1) * pagingMenu.getPageSize();
            return menus.stream()
                    .skip(skip)
                    .limit(pagingMenu.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return menus;
        }
    }
}
