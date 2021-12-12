package lv.javaguru.java2.oddJobs.core.services.find;

import lv.javaguru.java2.oddJobs.core.requests.find.FindClientsRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.Ordering;
import lv.javaguru.java2.oddJobs.core.requests.find.Paging;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.find.FindClientsResponse;
import lv.javaguru.java2.oddJobs.core.validations.find.FindClientsValidator;
import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.ClientRepository;
import lv.javaguru.java2.oddJobs.core.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class FindClientsService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private FindClientsValidator clientValidator;


    public FindClientsResponse execute(FindClientsRequest request) {

        List<CoreError> errors = clientValidator.validate(request);

        if (!errors.isEmpty()) {
            return new FindClientsResponse(null, errors);
        }

        List<Client> clients = find(request);
        clients = ordering(clients, request.getOrdering());
        clients = paging(clients, request.getPaging());

        return new FindClientsResponse(clients, null);
    }

    private List<Client> ordering(List<Client> clients, Ordering ordering) {
        if (orderingEnabled && ordering != null) {
            Comparator<Client> comparator = ordering.getOrderBy().equals("Name")
                    ? Comparator.comparing(Client::getClientName)
                    : Comparator.comparing(Client::getClientSurname);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return clients.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return clients;
        }
    }

    private List<Client> paging(List<Client> clients, Paging paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return clients.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return clients;
        }
    }

    private List<Client> find(FindClientsRequest request) {

        List<Client> clients = new ArrayList<>();
        if (request.isIdProvided() && !request.isNameProvided() && !request.isSurnameProvide()) {
            clients = clientRepository.findClientsById(request.getClientId());
        }
        if (!request.isIdProvided() && request.isNameProvided() && !request.isSurnameProvide()) {
            clients = clientRepository.findClientsByName(request.getClientName());
        }
        if (!request.isIdProvided() && !request.isNameProvided() && request.isSurnameProvide()) {
            clients = clientRepository.findClientBySurname(request.getClientSurname());
        }
        if (request.isNameProvided() && request.isSurnameProvide())
            clients = clientRepository.findClientByNameAndSurname(request.getClientName(),request.getClientSurname());

        if (request.isIdProvided() && request.isNameProvided() && request.isSurnameProvide()) {
                clients = clientRepository.findClientByIdAndNameAndSurname(request.getClientId(), request.getClientName(), request.getClientSurname());
            }
        return clients;
    }

}
