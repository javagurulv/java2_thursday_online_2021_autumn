package lv.javaguru.java2.oddJobs.core.services.add;

import lv.javaguru.java2.oddJobs.core.requests.add.AddClientRequest;
import lv.javaguru.java2.oddJobs.core.responce.add.AddClientResponse;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.validations.add.AddClientValidator;
import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.ClientRepository;
import lv.javaguru.java2.oddJobs.core.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddClientValidator validator;

    public AddClientResponse execute(AddClientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddClientResponse(errors);
        }
        Client client = new Client(request.getClientName(), request.getClientSurname(),request.getPersonalCode(),request.getCity());
        clientRepository.addClient(client);
        return new AddClientResponse(client);

    }
}
