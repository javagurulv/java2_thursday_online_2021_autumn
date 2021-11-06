package lv.javaguru.java2.oddJobs.core.services.add;

import lv.javaguru.java2.oddJobs.core.requests.add.AddAdvertismentRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.add.AddAdvertismentResponce;
import lv.javaguru.java2.oddJobs.core.responce.add.AddSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.validations.add.AddAdvertisementValidator;
import lv.javaguru.java2.oddJobs.core.validations.add.AddSpecialistValidator;
import lv.javaguru.java2.oddJobs.database.AdvertisementRepository;
import lv.javaguru.java2.oddJobs.domain.Advertisement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddAdvertismentService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AddAdvertisementValidator validator;


    public AddAdvertismentResponce execute(AddAdvertismentRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddAdvertismentResponce(errors);
        }
        Advertisement advertisement = new Advertisement(request.getAdvTitle(), request.getAdvDescription());
        advertisementRepository.addAdvertisement(advertisement);
        return new AddAdvertismentResponce(advertisement);

    }
}
