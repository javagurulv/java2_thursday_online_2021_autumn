package lv.javaguru.java2.hospital.visit.core.requests;

public class VisitPaging {

    private Integer pageNumber;
    private Integer pageSize;

    public VisitPaging(Integer pageNumber, Integer pageSize) {
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
