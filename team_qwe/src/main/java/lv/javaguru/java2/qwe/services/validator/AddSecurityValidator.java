package lv.javaguru.java2.qwe.services.validator;

import lv.javaguru.java2.qwe.request.SecurityRequest;

import java.util.List;

public abstract class AddSecurityValidator {

    public abstract List<String> validate(SecurityRequest request);

}