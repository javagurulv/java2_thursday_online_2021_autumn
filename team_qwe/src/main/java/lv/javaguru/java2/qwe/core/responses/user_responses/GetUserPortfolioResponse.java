package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;

public class GetUserPortfolioResponse extends CoreResponse {

    private final User user;

    public GetUserPortfolioResponse(List<CoreError> errors, User user) {
        super(errors);
        this.user = user;
    }

    public GetUserPortfolioResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}