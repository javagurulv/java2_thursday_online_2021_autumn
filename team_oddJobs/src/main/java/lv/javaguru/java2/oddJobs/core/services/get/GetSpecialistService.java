package lv.javaguru.java2.oddJobs.core.services.get;

import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.SpecialistRepository;
import lv.javaguru.java2.oddJobs.core.requests.get.GetSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.get.GetSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.validations.get.GetSpecialistValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetSpecialistService {
    @Autowired private SpecialistRepository specialistRepository;
    @Autowired private GetSpecialistValidator validator;

    public GetSpecialistResponse execute(GetSpecialistRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetSpecialistResponse(errors);
        }
        return specialistRepository.getById(request.getId())
                .map(GetSpecialistResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new GetSpecialistResponse(errors);
                });
    }
}
