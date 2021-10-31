package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.acceptance_test.AcceptanceTestForDatabase;
import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByTickerOrNameRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
public class FindSecurityByNameValidatorTest extends AcceptanceTestForDatabase {

    private final FindSecurityByNameValidator validator =
            super.getAppContext().getBean(FindSecurityByNameValidator.class);

    @Test
    public void shouldReturnEmptyList1() {
        FindSecurityByTickerOrNameRequest request = new FindSecurityByTickerOrNameRequest("Alibaba");
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList2() {
        FindSecurityByTickerOrNameRequest request = new FindSecurityByTickerOrNameRequest("Ali");
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnNameError1() {
        FindSecurityByTickerOrNameRequest request = new FindSecurityByTickerOrNameRequest("Al");
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Name");
        assertEquals(errorList.get(0).getMessage(), "minimum 3 symbols required!");
    }

    @Test
    public void shouldReturnNameError2() {
        FindSecurityByTickerOrNameRequest request = new FindSecurityByTickerOrNameRequest("");
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Name");
        assertEquals(errorList.get(0).getMessage(), "minimum 3 symbols required!");
    }

}*/
