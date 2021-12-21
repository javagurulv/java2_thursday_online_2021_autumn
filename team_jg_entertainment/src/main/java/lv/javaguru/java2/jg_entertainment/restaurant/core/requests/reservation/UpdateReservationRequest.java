package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation;

public class UpdateReservationRequest {

    private String reservationId;
    private String fieldToChange;
    private String enumEditReservation;
    private String changes;

    public UpdateReservationRequest() {
    }

    public UpdateReservationRequest(String reservationId, String fieldToChange, String enumEditReservation, String changes) {
        this.reservationId = reservationId;
        this.fieldToChange = fieldToChange;
        this.enumEditReservation = enumEditReservation;
        this.changes = changes;
    }

    

    public UpdateReservationRequest(String reservationId,
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

    public String getFieldToChange() {
        return fieldToChange;
    }

    public void setFieldToChange(String fieldToChange) {
        this.fieldToChange = fieldToChange;
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
