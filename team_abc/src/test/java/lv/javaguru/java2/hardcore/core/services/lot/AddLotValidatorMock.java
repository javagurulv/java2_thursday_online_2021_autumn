package lv.javaguru.java2.hardcore.core.services.lot;

import lv.javaguru.java2.hardcore.core.requests.lot.AddLotRequest;
import lv.javaguru.java2.hardcore.core.response.CoreError;
import lv.javaguru.java2.hardcore.core.services.lot.validators.AddLotValidator;

import java.util.List;

public class AddLotValidatorMock extends AddLotValidator {

    @Override
    public List<CoreError> validate(AddLotRequest request) {
        CoreError errors = new CoreError("testField", "testMessage");
        return List.of(errors);
    }
}
