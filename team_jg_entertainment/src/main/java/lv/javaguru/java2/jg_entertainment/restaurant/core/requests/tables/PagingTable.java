package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables;

public class PagingTable {

    private Integer pageNumber;
    private Integer pageSize;

    public PagingTable(Integer pageNumber,
                       Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
