package lv.javaguru.java2.services.Find;

import lv.javaguru.java2.Client;
import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.core.requests.Find.FindClientsRequest;
import lv.javaguru.java2.core.requests.Find.Ordering;
import lv.javaguru.java2.core.requests.Find.Paging;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.Find.FindClientsResponse;
import lv.javaguru.java2.core.validations.FindClientsRequestValidator;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@DIComponent
public class FindClientsService {

    @DIDependency
    private Database database;
    @DIDependency
    private FindClientsRequestValidator clientValidator;


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
