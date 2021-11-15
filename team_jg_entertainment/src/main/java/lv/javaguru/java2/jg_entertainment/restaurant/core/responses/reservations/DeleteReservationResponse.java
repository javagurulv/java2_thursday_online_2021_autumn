package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations;

import java.util.List;

public class DeleteReservationResponse extends CoreResponse {

    private boolean isTrue;

    public DeleteReservationResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public DeleteReservationResponse(boolean isTrueOrNot) {
        this.isTrue = isTrueOrNot;
    }

    public boolean isTrue() {
        return isTrue;
    }
}
