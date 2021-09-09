package lv.javaguru.java2.core.responce.Find;

import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.CoreResponse;

import java.util.List;

public class FindSpecialistByProfessionResponse extends CoreResponse {

    private Specialist foundedSpecialist;

    public FindSpecialistByProfessionResponse(Specialist foundedSpecialist) {
        this.foundedSpecialist = foundedSpecialist;
    }

    public FindSpecialistByProfessionResponse(List<CoreError> errors) {
        super(errors);
    }

    public Specialist getSpecialist() {
        return foundedSpecialist;
    }
}
