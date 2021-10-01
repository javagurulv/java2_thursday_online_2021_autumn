package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.Position;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.time.LocalDate;
import java.util.List;

public class GenerateUserPortfolioResponse extends CoreResponse {

    private final List<Position> portfolio;
    private final LocalDate portfolioGenerationDate;

    public GenerateUserPortfolioResponse(List<Position> portfolio, LocalDate portfolioGenerationDate) {
        this.portfolio = portfolio;
        this.portfolioGenerationDate = portfolioGenerationDate;
    }

    public GenerateUserPortfolioResponse(List<CoreError> errors, List<Position> portfolio,
                                         LocalDate portfolioGenerationDate) {
        super(errors);
        this.portfolio = portfolio;
        this.portfolioGenerationDate = portfolioGenerationDate;
    }

    public List<Position> getPortfolio() {
        return portfolio;
    }

    public LocalDate getPortfolioGenerationDate() {
        return portfolioGenerationDate;
    }

}