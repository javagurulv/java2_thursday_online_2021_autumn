package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.OrderingMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.PagingMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.SearchMenusRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.SearchMenusResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_menus.SearchMenusRequestValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchMenuServiceTest {

    @Mock private MenuRepository database;
    @Mock private SearchMenusRequestValidator validator;
    @InjectMocks private SearchMenusService service;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);
    }

    @Test
    public void shouldReturnErrorsEmptyFiled() {
        SearchMenusRequest request = new SearchMenusRequest(null, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("title", "must not be empty"));
        errors.add(new CoreError("description", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchMenusResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 2);
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnSearchByTitle() {
        SearchMenusRequest request = new SearchMenusRequest("title", null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu("title", "description", 10));
        Mockito.when(database.findByTitle("title")).thenReturn(menus);
        SearchMenusResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getMenus().size(), 1);
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnSearchPaging() {
        OrderingMenu orderingMenu = new OrderingMenu("title", "ASCENDING");
        PagingMenu pagingMenu = new PagingMenu(1,1);
        SearchMenusRequest request = new SearchMenusRequest("title", null, orderingMenu, pagingMenu);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu("title", "description", 10));
        menus.add(new Menu("title2", "description", 10));
        menus.add(new Menu("title3", "description", 10));
        Mockito.when(database.findByTitle("title")).thenReturn(menus);
        SearchMenusResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getMenus().size(), 1);
        assertEquals(response.getMenus().get(0).getTitle(), "title");
        Mockito.verifyNoInteractions(database);
    }
}
