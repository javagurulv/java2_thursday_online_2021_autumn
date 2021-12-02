package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation;

public class DeleteReservationRequest {

    private Long id;

    public DeleteReservationRequest() {
    }

    public DeleteReservationRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
