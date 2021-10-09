package lv.javaguru.java2.qwe.core.responses.data_responses;

import lv.javaguru.java2.qwe.core.domain.Bond;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;

public class AddBondResponse extends CoreResponse {

    private Bond newBond;

    public AddBondResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddBondResponse(Bond newBond) {
        this.newBond = newBond;
    }

    public Bond getNewBond() {
        return newBond;
    }

}