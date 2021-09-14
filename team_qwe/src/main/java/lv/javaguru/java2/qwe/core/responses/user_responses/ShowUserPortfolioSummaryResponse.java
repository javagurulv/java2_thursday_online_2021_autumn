package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;
import java.util.Map;

public class ShowUserPortfolioSummaryResponse extends CoreResponse {


    private int userRiskTolerance;
    private double userInitialInvestment;
    private double portfolioValue;
    private int amountOfPositions;
    private Map<String, Double> portfolioAllocation;
    private double avgWgtDividendYield;
    private double avgWgtRiskWeight;

    public ShowUserPortfolioSummaryResponse(int userRiskTolerance, double userInitialInvestment,
                                            double portfolioValue, int amountOfPositions,
                                            Map<String, Double> portfolioAllocation,
                                            double avgWgtDividendYield, double avgWgtRiskWeight) {
        this.userRiskTolerance = userRiskTolerance;
        this.userInitialInvestment = userInitialInvestment;
        this.portfolioValue = portfolioValue;
        this.amountOfPositions = amountOfPositions;
        this.portfolioAllocation = portfolioAllocation;
        this.avgWgtDividendYield = avgWgtDividendYield;
        this.avgWgtRiskWeight = avgWgtRiskWeight;
    }

    public ShowUserPortfolioSummaryResponse(List<CoreError> errors) {
        super(errors);
    }

    public int getUserRiskTolerance() {
        return userRiskTolerance;
    }

    public double getUserInitialInvestment() {
        return userInitialInvestment;
    }

    public double getPortfolioValue() {
        return portfolioValue;
    }

    public int getAmountOfPositions() {
        return amountOfPositions;
    }

    public Map<String, Double> getPortfolioAllocation() {
        return portfolioAllocation;
    }

    public double getAvgWgtDividendYield() {
        return avgWgtDividendYield;
    }

    public double getAvgWgtRiskWeight() {
        return avgWgtRiskWeight;
    }

}