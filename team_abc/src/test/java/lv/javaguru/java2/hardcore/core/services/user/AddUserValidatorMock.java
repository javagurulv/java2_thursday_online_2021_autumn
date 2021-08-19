package lv.javaguru.java2.hardcore.core.services.user;

import lv.javaguru.java2.hardcore.core.requests.user.AddUserRequest;
import lv.javaguru.java2.hardcore.core.response.CoreError;
import lv.javaguru.java2.hardcore.core.services.user.validators.AddUserValidator;

import java.util.List;

public class AddUserValidatorMock  extends AddUserValidator {

    @Override
    public List<CoreError> validate(AddUserRequest request) {
        CoreError error = new CoreError("testE", "testA");
        return List.of(error);
    }
}
