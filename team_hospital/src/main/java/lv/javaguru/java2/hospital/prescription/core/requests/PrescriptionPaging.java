package lv.javaguru.java2.hospital.prescription.core.requests;

public class PrescriptionPaging {

    private Integer pageNumber;
    private Integer pageSize;

    public PrescriptionPaging(Integer pageNumber, Integer pageSize) {
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
