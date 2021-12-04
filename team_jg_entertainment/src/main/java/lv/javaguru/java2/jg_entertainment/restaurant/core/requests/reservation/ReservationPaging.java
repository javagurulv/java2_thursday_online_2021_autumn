package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation;

public class ReservationPaging {

    private Integer pageNumber;
    private Integer pageSize;

    public ReservationPaging(Integer pageNumber, Integer pageSize) {
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
