package lv.javaguru.java2.qwe.core.responses.data_responses;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;

public class FindSecurityByNameResponse extends CoreResponse {

    private Security security;

    public FindSecurityByNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public FindSecurityByNameResponse(Security security) {
        this.security = security;
    }

    public Security getSecurity() {
        return security;
    }

}