package lv.javaguru.java2.qwe.services.validator;

import lv.javaguru.java2.qwe.request.AddUserRequest;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddUserValidatorTest {

    private final AddUserValidator validator = new AddUserValidator();

    @Test
    public void shouldReturnEmptyList() {
        AddUserRequest request = new AddUserRequest(
                "Alexander",
                "40",
                "SUPER_RICH",
                "1000000"
        );
        List<String> errorList = validator.validate(request);
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
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Name: 3 to 100 symbols required!");
    }

    @Test
    public void shouldReturnNameError2() {
        AddUserRequest request = new AddUserRequest(
                "Al",
                "40",
                "SUPER_RICH",
                "1000000"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Name: 3 to 100 symbols required!");
    }

    @Test
    public void shouldReturnNameError3() {
        AddUserRequest request = new AddUserRequest(
                "Alexanderrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr" +
                        "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr" +
                        "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr",
                "40",
                "SUPER_RICH",
                "1000000"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Name: 3 to 100 symbols required!");
    }

    @Test
    public void shouldReturnAgeError1() {
        AddUserRequest request = new AddUserRequest(
                "Alexander",
                "a",
                "SUPER_RICH",
                "1000000"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Age: wrong format! Must be integer!");
    }

    @Test
    public void shouldReturnAgeError2() {
        AddUserRequest request = new AddUserRequest(
                "Alexander",
                "",
                "SUPER_RICH",
                "1000000"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Age: wrong format! Must be integer!");
    }

    @Test
    public void shouldReturnAgeError3() {
        AddUserRequest request = new AddUserRequest(
                "Alexander",
                "15",
                "SUPER_RICH",
                "1000000"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Age: user of minimum 16 years old is allowed!");
    }

    @Test
    public void shouldReturnAgeError4() {
        AddUserRequest request = new AddUserRequest(
                "Alexander",
                "150",
                "SUPER_RICH",
                "1000000"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Age: user of maximum 130 years old is allowed!");
    }

    @Test
    public void shouldReturnTypeError() {
        AddUserRequest request = new AddUserRequest(
                "Alexander",
                "40",
                "",
                "1000000"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Type: is empty");
    }

    @Test
    public void shouldReturnInitialInvestmentError1() {
        AddUserRequest request = new AddUserRequest(
                "Alexander",
                "40",
                "SUPER_RICH",
                ""
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Initial investment: wrong format! Must be double!");
    }

    @Test
    public void shouldReturnInitialInvestmentError2() {
        AddUserRequest request = new AddUserRequest(
                "Alexander",
                "40",
                "SUPER_RICH",
                "9999.99"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Initial investment: minimum investment is 10,000.00 USD");
    }

    @Test
    public void shouldReturnInitialInvestmentError3() {
        AddUserRequest request = new AddUserRequest(
                "Alexander",
                "40",
                "SUPER_RICH",
                "150000000"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Initial investment: maximum investment is 100,000,000.00 USD");
    }

    @Test
    public void shouldReturnNameAgeInitialInvestmentErrors() {
        AddUserRequest request = new AddUserRequest(
                "Al",
                "12",
                "SUPER_RICH",
                "-1000000"
        );
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 3);
        assertTrue(errorList.contains("Name: 3 to 100 symbols required!"));
        assertTrue(errorList.contains("Age: user of minimum 16 years old is allowed!"));
        assertTrue(errorList.contains("Initial investment: minimum investment is 10,000.00 USD"));
    }

    @Test
    public void shouldReturnAllErrors() {
        AddUserRequest request = new AddUserRequest(
                "",
                "",
                "",
                ""
        );
        List<String> errorList = validator.validate(request);
        assertTrue(errorList.contains("Name: 3 to 100 symbols required!"));
        assertTrue(errorList.contains("Age: wrong format! Must be integer!"));
        assertTrue(errorList.contains("Type: is empty"));
        assertTrue(errorList.contains("Initial investment: wrong format! Must be double!"));
    }

}