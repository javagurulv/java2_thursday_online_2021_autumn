package lv.javaguru.java2.oddJobs.core.services.get;

import lv.javaguru.java2.oddJobs.core.requests.get.GetAllSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.get.GetAllSpecialistsResponse;
import lv.javaguru.java2.oddJobs.database.domainInterfaces.SpecialistRepository;
import lv.javaguru.java2.oddJobs.domain.Specialist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
@Transactional
public class GetAllSpecialistsService {

    @Autowired
    private SpecialistRepository specialistRepository;

    public GetAllSpecialistsResponse execute(GetAllSpecialistRequest request) {
        List<Specialist> specialists = specialistRepository.getAllSpecialist();
        return new GetAllSpecialistsResponse(specialists);
    }
}

