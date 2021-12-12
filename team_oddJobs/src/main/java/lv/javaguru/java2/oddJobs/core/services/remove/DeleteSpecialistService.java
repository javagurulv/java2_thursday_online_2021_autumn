package lv.javaguru.java2.oddJobs.core.services.remove;

import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.SpecialistRepository;
import lv.javaguru.java2.oddJobs.core.requests.remove.DeleteSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.remove.DeleteSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.validations.remove.DeleteSpecialistValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteSpecialistService {
    @Autowired private SpecialistRepository specialistRepository;
    @Autowired private DeleteSpecialistValidator validator;
    public DeleteSpecialistResponse execute(DeleteSpecialistRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteSpecialistResponse(errors);
        }
        return specialistRepository.getById(request.getId())
                .map(specialist -> {
                    specialistRepository.removeSpecialistById(request.getId());
                    return new DeleteSpecialistResponse(specialist);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new DeleteSpecialistResponse(errors);
                });
    }
}
