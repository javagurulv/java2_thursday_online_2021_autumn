package lv.javaguru.java2.qwe.core.responses;

import lv.javaguru.java2.qwe.Security;

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