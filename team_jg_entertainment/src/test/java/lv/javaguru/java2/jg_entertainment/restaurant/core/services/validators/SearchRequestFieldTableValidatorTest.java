package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.SearchTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchRequestFieldTableValidatorTest {

    private final SearchRequestFieldTableValidator validatorField = new SearchRequestFieldTableValidator();

    @Test
    public void shouldNotReturnErrorListWhenAllInformationProvided() {
        SearchTableRequest request = new SearchTableRequest("title", 1L);
        List<CoreError> errorList = validatorField.validatorField(request);
        assertEquals(errorList.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenAllInformationAreEmpty(){
        SearchTableRequest request= new SearchTableRequest(null, null);
        List<CoreError> errorList = validatorField.validatorField(request);
        assertEquals(errorList.size(), 2);
        assertEquals(errorList.get(0).getField(), "titleTable");
        assertEquals(errorList.get(0).getMessageError(), "can not be empty");
        assertEquals(errorList.get(1).getField(), "idTable");
        assertEquals(errorList.get(1).getMessageError(), "must not be null");
    }

    @Test
    public void shouldNotReturnErrorWhenIdFieldNotEmpty(){
        SearchTableRequest request = new SearchTableRequest(null, 1L);
        List<CoreError>errorList = validatorField.validatorField(request);
        assertEquals(errorList.size(),0);
    }
}