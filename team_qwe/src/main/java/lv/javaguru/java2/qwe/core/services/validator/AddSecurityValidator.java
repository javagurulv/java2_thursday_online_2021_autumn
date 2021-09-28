package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;

import java.util.List;

public abstract class AddSecurityValidator {

    public abstract List<CoreError> validate(CoreRequest request);

}