package lv.javaguru.java2.oddJobs.core.services.update;

import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.AdvertisementRepository;
import lv.javaguru.java2.oddJobs.core.requests.update.UpdateAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.update.UpdateAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.validations.update.UpdateAdvertisementRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UpdateAdvertisementService {
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private UpdateAdvertisementRequestValidator validator;

    public UpdateAdvertisementResponse execute(UpdateAdvertisementRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateAdvertisementResponse(errors);
        }

        return advertisementRepository.getById(request.getId())
                .map(advertisement -> {
                    advertisement.setAdvTitle(request.getNewAdvTitle());
                    advertisement.setAdvDescription(request.getNewAdvDescription());


                    return new UpdateAdvertisementResponse(advertisement);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new UpdateAdvertisementResponse(errors);
                });
    }
}
