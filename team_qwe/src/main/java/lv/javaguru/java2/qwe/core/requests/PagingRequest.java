package lv.javaguru.java2.qwe.core.requests;

public class PagingRequest extends SecurityRequest {

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