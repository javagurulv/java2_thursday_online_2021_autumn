package lv.javaguru.java2.qwe.core.responses.data_responses;

import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;

public class FindSecurityByTickerOrNameResponse extends CoreResponse {

    private Security security;

    public FindSecurityByTickerOrNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public FindSecurityByTickerOrNameResponse(Security security) {
        this.security = security;
    }

    public Security getSecurity() {
        return security;
    }

}