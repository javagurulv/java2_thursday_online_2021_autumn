package lv.javaguru.java2.qwe.core.requests.data_requests;

public class FilterStocksByAnyDoubleParameterRequest extends CoreRequest {

    private String parameter;
    private String operator;
    private String targetAmount;

    public FilterStocksByAnyDoubleParameterRequest() {}

    public FilterStocksByAnyDoubleParameterRequest(String parameter, String operator, String targetAmount) {
        this.parameter = parameter;
        this.operator = operator;
        this.targetAmount = targetAmount;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(String targetAmount) {
        this.targetAmount = targetAmount;
    }

}