package lv.javaguru.java2.qwe.core.requests.data_requests;

import java.util.List;

public class FilterStocksByMultipleParametersRequest extends CoreRequest {

    private List<CoreRequest> requestList;

    public FilterStocksByMultipleParametersRequest() {}

    public FilterStocksByMultipleParametersRequest(List<CoreRequest> requestList) throws NumberFormatException {
        this.requestList = requestList;
    }

    public List<CoreRequest> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<CoreRequest> requestList) {
        this.requestList = requestList;
    }

}