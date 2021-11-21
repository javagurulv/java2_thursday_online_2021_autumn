package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.AddMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.AddMenuResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_menus.AddMenuValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddMenuServiceTest {

    @Mock private MenuRepository database;
    @Mock private AddMenuValidator validator;
    @InjectMocks private AddMenuService service;

    @Test
    public void shouldReturnErrorsWhenValidationFails() {
        AddMenuRequest request = new AddMenuRequest(null, "description", 0.0);
        when(validator.validate(request)).thenReturn(List.of(new CoreError("title", "shouldn't be empty")));
        AddMenuResponse response = service.execute(request);
        assertTrue(response.hasErrors());
    }
}