package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.user_requests.AddUserRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.dependency_injection.ApplicationContext;
import lv.javaguru.java2.qwe.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddUserValidatorTest {

    private final ApplicationContext appContext =
            new DIApplicationContextBuilder().build("lv.javaguru.java2.qwe");

    private final AddUserValidator validator = appContext.getBean(AddUserValidator.class);

    @Test
    public void shouldReturnEmptyList() {
        AddUserRequest request = new AddUserRequest(
                "Michael",
                "40",
                "SUPER_RICH",
                "1000000"
        );
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnNameError1() {
        AddUserRequest request = new AddUserRequest(
                "",
                "40",
                "SUPER_RICH",
                "1000000"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Name");
        assertEquals(errorList.get(0).getMessage(), "3 to 100 symbols required!");
    }

    @Test
    public void shouldReturnNameError2() {
        AddUserRequest request = new AddUserRequest(
                "Al",
                "40",
                "SUPER_RICH",
                "1000000"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Name");
        assertEquals(errorList.get(0).getMessage(), "3 to 100 symbols required!");
    }

    @Test
    public void shouldReturnNameError3() {
        AddUserRequest request = new AddUserRequest(
                "Michaelrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr" +
                        "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr" +
                        "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr",
                "40",
                "SUPER_RICH",
                "1000000"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Name");
        assertEquals(errorList.get(0).getMessage(), "3 to 100 symbols required!");
    }

    @Test
    public void shouldReturnNameError4() {
        AddUserRequest request = new AddUserRequest(
                "Alexander",
                "40",
                "SUPER_RICH",
                "1000000"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Name");
        assertEquals(errorList.get(0).getMessage(), "user with such name already exists in database!");
    }

    @Test
    public void shouldReturnAgeError1() {
        AddUserRequest request = new AddUserRequest(
                "Michael",
                "a",
                "SUPER_RICH",
                "1000000"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Age");
        assertEquals(errorList.get(0).getMessage(), "wrong format! Must be integer!");
    }

    @Test
    public void shouldReturnAgeError2() {
        AddUserRequest request = new AddUserRequest(
                "Michael",
                "",
                "SUPER_RICH",
                "1000000"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Age");
        assertEquals(errorList.get(0).getMessage(), "wrong format! Must be integer!");
    }

    @Test
    public void shouldReturnAgeError3() {
        AddUserRequest request = new AddUserRequest(
                "Michael",
                "15",
                "SUPER_RICH",
                "1000000"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Age");
        assertEquals(errorList.get(0).getMessage(), "user of minimum 16 years old is allowed!");
    }

    @Test
    public void shouldReturnAgeError4() {
        AddUserRequest request = new AddUserRequest(
                "Michael",
                "150",
                "SUPER_RICH",
                "1000000"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Age");
        assertEquals(errorList.get(0).getMessage(), "user of maximum 130 years old is allowed!");
    }

    @Test
    public void shouldReturnTypeError() {
        AddUserRequest request = new AddUserRequest(
                "Michael",
                "40",
                "",
                "1000000"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Type");
        assertEquals(errorList.get(0).getMessage(), "is empty");
    }

    @Test
    public void shouldReturnInitialInvestmentError1() {
        AddUserRequest request = new AddUserRequest(
                "Michael",
                "40",
                "SUPER_RICH",
                ""
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Initial investment");
        assertEquals(errorList.get(0).getMessage(), "wrong format! Must be double!");
    }

    @Test
    public void shouldReturnInitialInvestmentError2() {
        AddUserRequest request = new AddUserRequest(
                "Michael",
                "40",
                "SUPER_RICH",
                "9999.99"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Initial investment");
        assertEquals(errorList.get(0).getMessage(), "minimum investment is 10,000.00 USD");
    }

    @Test
    public void shouldReturnInitialInvestmentError3() {
        AddUserRequest request = new AddUserRequest(
                "Michael",
                "40",
                "SUPER_RICH",
                "150000000"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Initial investment");
        assertEquals(errorList.get(0).getMessage(), "maximum investment is 100,000,000.00 USD");
    }

    @Test
    public void shouldReturnNameAgeInitialInvestmentErrors() {
        AddUserRequest request = new AddUserRequest(
                "Al",
                "12",
                "SUPER_RICH",
                "-1000000"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 3);
        assertTrue(errorList.contains(new CoreError("Name", "3 to 100 symbols required!")));
        assertTrue(errorList.contains(new CoreError("Age", "user of minimum 16 years old is allowed!")));
        assertTrue(errorList.contains(new CoreError("Initial investment", "minimum investment is 10,000.00 USD")));
    }

    @Test
    public void shouldReturnAllErrors() {
        AddUserRequest request = new AddUserRequest(
                "",
                "",
                "",
                ""
        );
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.contains(new CoreError("Name", "3 to 100 symbols required!")));
        assertTrue(errorList.contains(new CoreError("Age", "wrong format! Must be integer!")));
        assertTrue(errorList.contains(new CoreError("Type", "is empty")));
        assertTrue(errorList.contains(new CoreError("Initial investment", "wrong format! Must be double!")));
    }

}