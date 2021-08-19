package lv.javaguru.java2.hardcore.core.services.user;

import lv.javaguru.java2.hardcore.core.database.UserRepository;
import lv.javaguru.java2.hardcore.core.domain.User;
import lv.javaguru.java2.hardcore.core.requests.user.LoginRequest;
import lv.javaguru.java2.hardcore.core.response.CoreError;
import lv.javaguru.java2.hardcore.core.response.user.LoginResponse;
import lv.javaguru.java2.hardcore.core.services.user.validators.LoginValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {


    @Mock private UserRepository userRepository;
    @Mock private LoginValidator validator;
    @Mock private User user;


    @InjectMocks
    private LoginService service;

    @Test
    public void loginError() {
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("test1", "test1"));
        LoginRequest request = new LoginRequest("test", "test");
        Mockito.when(validator.validate(request)).thenReturn(errors);
        LoginResponse response = service.execute(request);
        assertTrue(response.hasErrors());
    }


    @Test
    public void loginAccept() {

        LoginRequest request = new LoginRequest("test", "test");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        LoginResponse response = service.execute(request);
        User user = new User("test", "test");
//        Mockito.verify(userDatabase).logIn(user);
        assertFalse(response.hasErrors());


    }

}