package lv.javaguru.java2.core.responce.Add;

import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.CoreResponse;

import java.util.List;

public class AddSpecialistResponse extends CoreResponse {

    private Specialist specialist;

    public AddSpecialistResponse(Specialist specialist) {
        this.specialist = specialist;
    }

    public AddSpecialistResponse(List<CoreError> errors) {
        super(errors);

    }
    public Specialist getSpecialist() {
        return specialist;
    }
}
