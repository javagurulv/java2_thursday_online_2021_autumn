package lv.javaguru.java2.oddJobs.core.services.remove;

import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveAdvertismentRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.remove.RemoveAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.validations.remove.RemoveAdvertisementValidator;
import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
@Transactional
public class RemoveAdvertisementService {
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired private RemoveAdvertisementValidator validator;


    public RemoveAdvertisementResponse execute (RemoveAdvertismentRequest request){
        List<CoreError> errors =validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveAdvertisementResponse(errors);
        }
        boolean isAdvertisementRemoved = advertisementRepository.removeAdvertisement(request.getAdvId(), request.getAdvTitle());
        return new RemoveAdvertisementResponse(isAdvertisementRemoved);

        }


    }









