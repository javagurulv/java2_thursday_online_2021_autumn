package lv.javaguru.java2.oddJobs.core.services.add;

import lv.javaguru.java2.oddJobs.core.requests.add.AddAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.add.AddAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.validations.add.AddAdvertisementValidator;
import lv.javaguru.java2.oddJobs.database.domainInterfaces.AdvertisementRepository;
import lv.javaguru.java2.oddJobs.domain.Advertisement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddAdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AddAdvertisementValidator validator;


    public AddAdvertisementResponse execute(AddAdvertisementRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddAdvertisementResponse(errors);
        }
        Advertisement advertisement = new Advertisement(request.getAdvTitle(), request.getAdvDescription());
        advertisementRepository.addAdvertisement(advertisement);
        return new AddAdvertisementResponse(advertisement);

    }
}
