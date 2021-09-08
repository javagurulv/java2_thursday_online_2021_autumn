package lv.javaguru.java2.qwe.core.responses;

import lv.javaguru.java2.qwe.Security;

public class FindSecurityByNameResponse extends CoreResponse {

    private Security security;

    public FindSecurityByNameResponse() {
    }

    public FindSecurityByNameResponse(Security security) {
        this.security = security;
    }

    public Security getSecurity() {
        return security;
    }

}