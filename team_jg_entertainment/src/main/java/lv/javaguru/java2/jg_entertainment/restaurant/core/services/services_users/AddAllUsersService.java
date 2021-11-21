package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.AddUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.AddUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users.AddUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddAllUsersService {

    @Autowired private UsersRepository database;
    @Autowired private AddUserValidator validator;

    public AddUsersResponse execute(AddUserRequest request) {
        List<CoreError> coreErrors = validator.coreErrors(request);
        if (!coreErrors.isEmpty()) {
            return new AddUsersResponse(coreErrors);
        }
        User user = new User(request.getName(), request.getSurname(), request.getTelephone());
        database.saveUserToRestaurantList(user);
        return new AddUsersResponse(user);
    }
}
