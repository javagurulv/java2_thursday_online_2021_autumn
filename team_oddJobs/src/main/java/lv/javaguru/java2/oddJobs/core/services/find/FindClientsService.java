package lv.javaguru.java2.oddJobs.core.services.find;

import lv.javaguru.java2.oddJobs.core.requests.find.FindClientsRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.Ordering;
import lv.javaguru.java2.oddJobs.core.requests.find.Paging;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.find.FindClientsResponse;
import lv.javaguru.java2.oddJobs.core.validations.FindClientsValidator;
import lv.javaguru.java2.oddJobs.database.Database;
import lv.javaguru.java2.oddJobs.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindClientsService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired private Database database;
    @Autowired private FindClientsValidator clientValidator;


    public FindClientsResponse execute(FindClientsRequest request) {

        List<CoreError> errors = clientValidator.validate(request);

        if (!errors.isEmpty()) {
            return new FindClientsResponse(null, errors);
        }

        List<Client> clients = find(request);
        clients = order(clients, request.getOrdering());
        clients = paging(clients, request.getPaging());

        return new FindClientsResponse(clients, null);
    }

    private List<Client> order(List<Client> clients, Ordering ordering) {

        if (ordering != null) {
            Comparator<Client> comparator = ordering.getOrderBy().equals("clientSurname")
                    ? Comparator.comparing(Client::getClientSurname)
                    : Comparator.comparing(Client::getClientName);

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
            clients = database.findClientsById(request.getClientId());
        }
        if (!request.isIdProvided() && request.isNameProvided() && !request.isSurnameProvide()) {
            clients = database.findClientsByName(request.getClientName());
        }
        if (!request.isIdProvided() && !request.isNameProvided() && request.isSurnameProvide()) {
            clients = database.findClientBySurname(request.getClientSurname());
        }

        if (request.isIdProvided() && request.isNameProvided() && request.isSurnameProvide()) {
            clients = database.findClientByIdAndNameAndSurname(request.getClientId(), request.getClientName(), request.getClientSurname());
        }
        return clients;
    }

}
