package lv.javaguru.java2.oddJobs.core.services.remove;

import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.ClientRepository;
import lv.javaguru.java2.oddJobs.core.requests.remove.DeleteClientRequest;
import lv.javaguru.java2.oddJobs.core.requests.remove.DeleteSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveClientRequest;
import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.remove.DeleteClientResponse;
import lv.javaguru.java2.oddJobs.core.response.remove.DeleteSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.response.remove.RemoveClientResponse;
import lv.javaguru.java2.oddJobs.core.response.remove.RemoveSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.validations.remove.DeleteClientValidator;
import lv.javaguru.java2.oddJobs.core.validations.remove.RemoveClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class DeleteClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DeleteClientValidator validator;

    public DeleteClientResponse execute(DeleteClientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteClientResponse(errors);
        }
        return clientRepository.getById(request.getId())
                .map(client -> {
                    clientRepository.removeClientById(request.getId());
                    return new DeleteClientResponse(client);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new DeleteClientResponse(errors);
                });
    }
}
