package lv.javaguru.java2.oddJobs.core.services.add;


import lv.javaguru.java2.oddJobs.core.requests.add.AddSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.add.AddSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.validations.add.AddSpecialistValidator;
import lv.javaguru.java2.oddJobs.database.domainInterfaces.SpecialistRepository;
import lv.javaguru.java2.oddJobs.domain.Specialist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddSpecialistService {
    @Autowired
    private SpecialistRepository specialistRepository;
    @Autowired
    private AddSpecialistValidator validator;


    public AddSpecialistResponse execute(AddSpecialistRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddSpecialistResponse(errors);
        }
        Specialist specialist = new Specialist(request.getName(), request.getSurname(), request.getProfession(),request.getPersonalCode(),request.getCity());
        specialistRepository.addSpecialist(specialist);
        return new AddSpecialistResponse(specialist);

    }

}
