package lv.javaguru.java2.oddJobs.core.services.get;

import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.ClientRepository;
import lv.javaguru.java2.oddJobs.core.requests.get.GetClientRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.get.GetClientResponse;
import lv.javaguru.java2.oddJobs.core.validations.get.GetClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GetClientService {

    @Autowired private ClientRepository clientRepository;
    @Autowired private GetClientValidator validator;

    public GetClientResponse execute(GetClientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetClientResponse(errors);
        }
        return clientRepository.getById(request.getId())
                .map(GetClientResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new GetClientResponse(errors);
                });
    }
}
