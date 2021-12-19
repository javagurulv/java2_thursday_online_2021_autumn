package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations;

import java.util.List;

public class UpdateReservationResponse extends CoreResponse{

    private boolean editReservation;

    public UpdateReservationResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public UpdateReservationResponse(boolean editReservation) {
        this.editReservation = editReservation;
    }

    public boolean isEditReservation(){
        return editReservation;
    }
}
