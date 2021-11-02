package lv.javaguru.java2.qwe.core.requests.data_requests;

public class PagingRequest extends CoreRequest {

    private final String pageNumber;
    private final String pageSize;

    public PagingRequest(String pageNumber, String pageSize) {
        if (!pageNumber.isEmpty() && !pageSize.isEmpty()) {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
        }
        else if (pageNumber.isEmpty() && pageSize.isEmpty()) {
            this.pageNumber = "0";
            this.pageSize = "1000";
        }
        else {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
        }
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public String getPageSize() {
        return pageSize;
    }

}