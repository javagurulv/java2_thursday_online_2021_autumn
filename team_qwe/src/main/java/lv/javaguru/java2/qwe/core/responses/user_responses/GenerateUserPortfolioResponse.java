package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;

public class GenerateUserPortfolioResponse extends CoreResponse {

    private final User user;

    public GenerateUserPortfolioResponse(User user) {
        this.user = user;
    }

    public GenerateUserPortfolioResponse(List<CoreError> errors, User user) {
        super(errors);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}