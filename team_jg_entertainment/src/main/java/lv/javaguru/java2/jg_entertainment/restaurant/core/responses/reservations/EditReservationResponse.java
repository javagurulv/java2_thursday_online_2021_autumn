package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations;

import java.util.List;

public class EditReservationResponse extends CoreResponse{

    private boolean editReservation;

    public EditReservationResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public EditReservationResponse(boolean editReservation) {
        this.editReservation = editReservation;
    }

    public boolean isEditReservation(){
        return editReservation;
    }
}
