package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.AddMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.AddMenuResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu.matcher.MenuMatcher;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_menus.AddMenuValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddMenuServiceTest {

    @Mock private MenuRepository database;
    @Mock private AddMenuValidator validator;
    @InjectMocks private AddMenuService service;

    @Test
    public void shouldReturnErrorsWhenValidationFails() {
        AddMenuRequest request = new AddMenuRequest(null, "description", 0.0);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("title", "must not be empty"));
        when(validator.validate(request)).thenReturn(errors);
        AddMenuResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldAddMenu(){
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddMenuRequest request = new AddMenuRequest("title", "description", 0.0);
        AddMenuResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(database).save(argThat(new MenuMatcher("title", "description", 0.0)));
    }
}