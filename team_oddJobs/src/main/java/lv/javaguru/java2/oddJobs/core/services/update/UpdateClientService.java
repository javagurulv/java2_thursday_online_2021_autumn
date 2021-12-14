package lv.javaguru.java2.oddJobs.core.services.update;

import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.ClientRepository;
import lv.javaguru.java2.oddJobs.core.requests.update.UpdateClientRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.update.UpdateClientResponse;
import lv.javaguru.java2.oddJobs.core.validations.update.UpdateClientRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UpdateClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired private UpdateClientRequestValidator validator;

    public UpdateClientResponse execute(UpdateClientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateClientResponse(errors);
        }

        return clientRepository.getById(request.getId())
                .map(client -> {
                    client.setClientName(request.getNewClientName());
                    client.setClientSurname(request.getNewClientSurname());
                    client.setClientPersonalCode(request.getNewClientPersonalCode());
                    client.setClientCity(request.getNewClientCity());

                    return new  UpdateClientResponse(client);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new UpdateClientResponse(errors);
                });
    }
}
