package lv.javaguru.java2.services.Remove;

import lv.javaguru.java2.core.requests.Remove.RemoveAdvertismentRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.Remove.RemoveAdvertismentResponse;
import lv.javaguru.java2.core.validations.RemoveAdvertismentValidator;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;

import java.util.List;
@DIComponent
public class RemoveAdvertismentService {
    @DIDependency
    private Database database;
    @DIDependency private RemoveAdvertismentValidator validator;


    public RemoveAdvertismentResponse execute (RemoveAdvertismentRequest request){
        List<CoreError> errors =validator.validate(request);
        if ((!errors.isEmpty())) {
            return new RemoveAdvertismentResponse(errors);
        }
        boolean isAdvertismetRemoved = database.removeAdvertisement(request.getAdvId(), request.getAdvTitle());
        return new RemoveAdvertismentResponse(isAdvertismetRemoved);

        }


    }









