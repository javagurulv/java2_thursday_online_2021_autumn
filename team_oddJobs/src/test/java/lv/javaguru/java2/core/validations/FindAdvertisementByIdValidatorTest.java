package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Find.FindAdvertisementByIdRequest;
import lv.javaguru.java2.core.requests.Find.FindAdvertisementByTitleRequest;
import lv.javaguru.java2.core.responce.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindAdvertisementByIdValidatorTest {

    private FindAdvertisementByIdValidator validator = new FindAdvertisementByIdValidator();

    @Test
    public void shouldReturnError() {
        FindAdvertisementByIdRequest request = new FindAdvertisementByIdRequest(0);
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList() {
        FindAdvertisementByIdRequest request = new FindAdvertisementByIdRequest(1);
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

}