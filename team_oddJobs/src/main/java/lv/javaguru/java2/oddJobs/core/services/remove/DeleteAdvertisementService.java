package lv.javaguru.java2.oddJobs.core.services.remove;

import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.AdvertisementRepository;
import lv.javaguru.java2.oddJobs.core.requests.remove.DeleteAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.remove.DeleteAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.validations.remove.DeleteAdvertisementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class DeleteAdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private DeleteAdvertisementValidator validator;

    public DeleteAdvertisementResponse execute(DeleteAdvertisementRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteAdvertisementResponse(errors);
        }
        return advertisementRepository.getById(request.getId())
                .map(advertisement -> {
                    advertisementRepository.removeAdvertisementById(request.getId());
                    return new DeleteAdvertisementResponse(advertisement);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new DeleteAdvertisementResponse(errors);
                });
    }
}
