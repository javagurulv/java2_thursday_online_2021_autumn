package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.ArrayList;
import java.util.List;

public class GetUserPortfolioResponse extends CoreResponse {

    private final User user;
    private final List<Position> portfolio;

    public GetUserPortfolioResponse(List<CoreError> errors, User user) {
        super(errors);
        this.user = user;
        this.portfolio = new ArrayList<>();
    }

    public GetUserPortfolioResponse(User user, List<Position> portfolio) {
        this.user = user;
        this.portfolio = portfolio;
    }

    public User getUser() {
        return user;
    }

    public List<Position> getPortfolio() {
        return portfolio;
    }

}