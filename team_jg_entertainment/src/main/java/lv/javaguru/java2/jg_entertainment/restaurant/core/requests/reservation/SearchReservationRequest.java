package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation;

public class SearchReservationRequest {

    private String reservationId;
    private String usedId;
    private String date;
    private ReservationOrdering ordering;
    private ReservationPaging paging;

    public SearchReservationRequest() {
    }

    public SearchReservationRequest(String reservationId, String usedId, String date) {
        this.reservationId = reservationId;
        this.usedId = usedId;
        this.date = date;
    }

    public SearchReservationRequest(String reservationId, String date) {
        this.reservationId = reservationId;
        this.date = date;
    }

    public SearchReservationRequest(String reservationId, String usedId, String date, ReservationOrdering ordering) {
        this.reservationId = reservationId;
        this.usedId = usedId;
        this.date = date;
        this.ordering = ordering;
    }

    public SearchReservationRequest(String reservationId, String usedId, String date, ReservationOrdering ordering, ReservationPaging paging) {
        this.reservationId = reservationId;
        this.usedId = usedId;
        this.date = date;
        this.ordering = ordering;
        this.paging = paging;
    }

    public SearchReservationRequest(String reservationId, String usedId, String date, ReservationPaging paging) {
        this.reservationId = reservationId;
        this.usedId = usedId;
        this.date = date;
        this.paging = paging;
    }

    public SearchReservationRequest(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getUsedId() {
        return usedId;
    }

    public void setUsedId(String usedId) {
        this.usedId = usedId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isReservationIdProvided() {
        return this.reservationId != null && !this.reservationId.isEmpty();
    }

    public boolean isUserIdProvided() {
        return this.usedId != null && !this.usedId.isEmpty();
    }

    public boolean isDateProvided() {
        return this.date != null && !this.date.isEmpty();
    }

    public ReservationOrdering getOrdering() {
        return ordering;
    }

    public ReservationPaging getPaging() {
        return paging;
    }
}
