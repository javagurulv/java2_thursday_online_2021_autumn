package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.acceptance_test.AcceptanceTestForDatabase;
import lv.javaguru.java2.qwe.core.requests.user_requests.GenerateUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.RemoveUserRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.services.user_services.GenerateUserPortfolioService;
import lv.javaguru.java2.qwe.dependency_injection.ApplicationContext;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.importDataForTests;

public class RemoveUserValidatorTest extends AcceptanceTestForDatabase {

    private final RemoveUserValidator validator =
            super.getAppContext().getBean(RemoveUserValidator.class);

    @Test
    public void shouldReturnEmptyList() {
        RemoveUserRequest request = new RemoveUserRequest("Vladimir");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnError1() {
        RemoveUserRequest request = new RemoveUserRequest("");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "No user with such name!");
    }

    @Test
    public void shouldReturnError2() {
        RemoveUserRequest request = new RemoveUserRequest("Vlad");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "No user with such name!");
    }

    @Test
    public void shouldReturnError3() {
        ApplicationContext context = super.getAppContext();
        importDataForTests(context);
        GenerateUserPortfolioService service =
                context.getBean(GenerateUserPortfolioService.class);
        service.execute(new GenerateUserPortfolioRequest("Tatyana"));
        RemoveUserRequest request = new RemoveUserRequest("Tatyana");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(),
                "can't remove user, because there are securities in the portfolio!");
    }

}