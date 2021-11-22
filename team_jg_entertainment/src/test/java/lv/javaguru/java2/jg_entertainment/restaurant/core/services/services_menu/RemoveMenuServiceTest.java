package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.RemoveMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.RemoveMenuResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_menus.RemoveMenuRequestValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class RemoveMenuServiceTest {

    @Mock private MenuRepository database;
    @Mock private RemoveMenuRequestValidator validator;
    @InjectMocks private RemoveMenuService service;

    @Test
    public void shouldReturnErrorWhenIdNull(){
        RemoveMenuRequest request = new RemoveMenuRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("id", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        RemoveMenuResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
    }

    @Test
    public void shouldDeleteMenu(){
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.deleteByNr(10L)).thenReturn(true);
        RemoveMenuRequest request = new RemoveMenuRequest(10L);
        RemoveMenuResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isMenuRemoved());
    }
}