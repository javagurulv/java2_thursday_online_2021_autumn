package lv.javaguru.java2.oddJobs.core.services.remove;

import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveAdvertismentRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.remove.RemoveAdvertismentResponse;
import lv.javaguru.java2.oddJobs.core.validations.RemoveAdvertismentValidator;
import lv.javaguru.java2.oddJobs.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RemoveAdvertismentService {
    @Autowired
    private Database database;
    @Autowired private RemoveAdvertismentValidator validator;


    public RemoveAdvertismentResponse execute (RemoveAdvertismentRequest request){
        List<CoreError> errors =validator.validate(request);
        if ((!errors.isEmpty())) {
            return new RemoveAdvertismentResponse(errors);
        }
        boolean isAdvertismetRemoved = database.removeAdvertisement(request.getAdvId(), request.getAdvTitle());
        return new RemoveAdvertismentResponse(isAdvertismetRemoved);

        }


    }









