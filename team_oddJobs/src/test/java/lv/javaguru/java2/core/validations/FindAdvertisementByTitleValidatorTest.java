package lv.javaguru.java2.core.validations;


import lv.javaguru.java2.core.requests.Find.FindAdvertisementByTitleRequest;
import lv.javaguru.java2.core.responce.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FindAdvertisementByTitleValidatorTest {

    private FindAdvertisementByTitleValidator validator = new FindAdvertisementByTitleValidator();

    @Test
    public void shouldReturnEmptyList() {
        FindAdvertisementByTitleRequest request = new FindAdvertisementByTitleRequest("Title");
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnTitleError() {
        FindAdvertisementByTitleRequest request = new FindAdvertisementByTitleRequest("");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
    }
}