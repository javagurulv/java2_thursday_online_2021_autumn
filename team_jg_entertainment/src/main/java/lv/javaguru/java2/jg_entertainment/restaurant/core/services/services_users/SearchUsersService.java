package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.SearchUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.SearchUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users.SearchUsersRequestValidator;
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
public class SearchUsersService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired private UsersRepository database;
    @Autowired private SearchUsersRequestValidator validator;

    public SearchUsersResponse execute(SearchUsersRequest request) {

        List<CoreError> errors = validator.validator(request);
        if (!errors.isEmpty()) {
            return new SearchUsersResponse(null, errors);
        }
        List<User> userList = search(request);
        userList = order(userList, request.getOrdering());
        userList = paging(userList, request.getPaging());

        return new SearchUsersResponse(userList, null);
    }

    private List<User> order(List<User> users, Ordering ordering) {

        if (ordering != null) {
            Comparator<User> comparator = ordering.getOrderBy().equals("name")
                    ? Comparator.comparing(User::getUserName)
                    : Comparator.comparing(User::getSurname);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return users.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } else {
            return users;
        }
    }

    private List<User> search(SearchUsersRequest request) {

        List<User> users = new ArrayList<>();
        if (request.isNameProvided() && !request.isSurnameProvided()) {
            users = database.findByNameUser(request.getUserName());
        }
        if (!request.isNameProvided() && request.isSurnameProvided()) {
            users = database.findBySurnameUser(request.getUsersSurname());
        }
        if (request.isNameProvided() && request.isSurnameProvided()) {
            users = database.findByNameAndSurname(request.getUserName(), request.getUsersSurname());
        }
        if (request.isIDProvided() && !request.isNameProvided() && !request.isSurnameProvided()) {
            users = database.findUserById(request.getUsersId());
        }
        if (request.isNameProvided() && request.isTelephoneNumberProvided()) {
            users = database.findUsersByNameAndTelephoneNumber(request.getUserName(), request.getTelephoneNumber());
        }
        return users;
    }

    //paging
    private List<User> paging(List<User> users, Paging paging) {

        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return users.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return users;
        }
    }
}
