package lv.javaguru.java2.hospital.doctor.core.requests;

public class DoctorPaging {

    private Integer pageNumber;
    private Integer pageSize;

    public DoctorPaging(Integer pageNumber, Integer pageSize) {
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
