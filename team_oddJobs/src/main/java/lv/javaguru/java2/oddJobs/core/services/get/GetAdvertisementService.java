package lv.javaguru.java2.oddJobs.core.services.get;

import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.AdvertisementRepository;
import lv.javaguru.java2.oddJobs.core.requests.get.GetAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.requests.get.GetSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.get.GetAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.response.get.GetSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.validations.get.GetAdvertisementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GetAdvertisementService {

    @Autowired private AdvertisementRepository advertisementRepository;
    @Autowired private GetAdvertisementValidator validator;

    public GetAdvertisementResponse execute(GetAdvertisementRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetAdvertisementResponse(errors);
        }
        return advertisementRepository.getById(request.getId())
                .map(GetAdvertisementResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new GetAdvertisementResponse(errors);
                });
    }

}
