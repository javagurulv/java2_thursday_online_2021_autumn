package lv.javaguru.java2.qwe.core.requests.data_requests;

public class FilterStockByAnyDoubleParameterRequest extends SecurityRequest {

    private final String parameter;
    private final String operator;
    private final String targetAmount;

    public FilterStockByAnyDoubleParameterRequest(String parameter, String operator, String targetAmount) {
        this.parameter = parameter;
        this.operator = operator;
        this.targetAmount = targetAmount;
    }

    public String getParameter() {
        return parameter;
    }

    public String getOperator() {
        return operator;
    }

    public String getTargetAmount() {
        return targetAmount;
    }

}