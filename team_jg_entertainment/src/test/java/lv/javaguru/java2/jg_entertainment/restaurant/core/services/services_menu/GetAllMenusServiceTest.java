package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.GetAllMenusRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.GetAllMenusResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class GetAllMenusServiceTest {

    @Mock private MenuRepository database;
    @InjectMocks private GetAllMenusService service;

    @Test
    public void shouldGetMenu() {
        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu("title", "description", 0.0));
        Mockito.when(database.getAllMenus()).thenReturn(menus);
        GetAllMenusRequest request = new GetAllMenusRequest();
        GetAllMenusResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getMenus().size(), 1);
        assertEquals(response.getMenus().get(0).getTitle(), "title");
        assertEquals(response.getMenus().get(0).getDescription(), "description");
        assertEquals(response.getMenus().get(0).getPrice(), 0.0);
    }
}