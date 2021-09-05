package lv.javaguru.java2.core.responce.Remove;

import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.CoreResponse;

import java.util.List;

public class RemoveSpecialistResponse extends CoreResponse {
    private boolean specialistRemoved;

    public RemoveSpecialistResponse(boolean specialistRemoved) {
        this.specialistRemoved = specialistRemoved;
    }

    public RemoveSpecialistResponse(List<CoreError> errors){
        super(errors);
    }
    public boolean isSpecialistRemoved() {
        return specialistRemoved;
    }
}
