package lv.javaguru.java2.core.responce.Find;

import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.CoreResponse;

import java.util.List;

public class FindSpecialistResponse extends CoreResponse {

    private List<Specialist> specialists;


    public FindSpecialistResponse(List<Specialist> specialists, List<CoreError> errors) {
        super(errors);
        this.specialists = specialists;
    }

    public List<Specialist> getSpecialists() {
        return specialists;
    }
}
