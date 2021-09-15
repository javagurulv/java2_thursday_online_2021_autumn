package lv.javaguru.java2.hospital.patient.requests;

public class Paging {
    private String pageNumber;
    private String pageSize;

    public Paging(String pageNumber, String pageSize) {
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
