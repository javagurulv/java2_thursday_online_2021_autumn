package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation;

public class EditReservationRequest {

    private String reservationId;
    private String enumEditReservation;
    private String changes;

    public EditReservationRequest() {
    }

    public EditReservationRequest(String reservationId,
                                  String enumEditReservation,
                                  String changes) {
        this.reservationId = reservationId;
        this.enumEditReservation = enumEditReservation;
        this.changes = changes;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getEnumEditReservation() {
        return enumEditReservation;
    }

    public String getChanges() {
        return changes;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public void setEnumEditReservation(String enumEditReservation) {
        this.enumEditReservation = enumEditReservation;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }
}
