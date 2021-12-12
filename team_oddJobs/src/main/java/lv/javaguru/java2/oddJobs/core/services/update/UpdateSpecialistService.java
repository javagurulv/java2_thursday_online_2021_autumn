package lv.javaguru.java2.oddJobs.core.services.update;

import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.SpecialistRepository;
import lv.javaguru.java2.oddJobs.core.requests.update.UpdateSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.update.UpdateSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.validations.update.UpdateSpecialistRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UpdateSpecialistService {

    @Autowired
    private SpecialistRepository specialistRepository;
    @Autowired private UpdateSpecialistRequestValidator validator;

    public UpdateSpecialistResponse execute(UpdateSpecialistRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateSpecialistResponse(errors);
        }

        return specialistRepository.getById(request.getId())
                .map(specialist -> {
                    specialist.setSpecialistName(request.getNewName());
                    specialist.setSpecialistSurname(request.getNewSurname());
                    specialist.setSpecialistProfession(request.getNewProfession());
                    specialist.setPersonalCode(request.getNewPersonalCode());
                    specialist.setCity(request.getNewCity());

                    return new  UpdateSpecialistResponse(specialist);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new UpdateSpecialistResponse(errors);
                });
    }
}
