package lv.javaguru.java2.qwe.core.responses;

import lv.javaguru.java2.qwe.Bond;

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