package lv.javaguru.java2.qwe.core.requests.data_requests;

public class PagingRequest extends CoreRequest {

    private final String pageNumber;
    private final String pageSize;

    public PagingRequest(String pageNumber, String pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public String getPageSize() {
        return pageSize;
    }

}