package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.data_requests.SecurityRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;

import java.util.List;

public abstract class AddSecurityValidator {

    public abstract List<CoreError> validate(SecurityRequest request);

}