package lv.javaguru.java2.oddJobs.core.services.remove;


import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveClientRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.remove.RemoveClientResponse;
import lv.javaguru.java2.oddJobs.core.validations.remove.RemoveClientValidator;
import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class RemoveClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RemoveClientValidator validator;

    public RemoveClientResponse execute(RemoveClientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveClientResponse(errors);
        }
        boolean isClientRemoved = clientRepository.removeClient(request.getClientId(), request.getClientName(), request.getClientSurname());
        return new RemoveClientResponse(isClientRemoved);
    }
}
