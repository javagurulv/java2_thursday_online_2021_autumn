package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.ShowAllUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.ShowAllUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ShowListUsersService {

   @Autowired private UsersRepository database;

    public ShowAllUsersResponse execute(ShowAllUsersRequest request) {
        List<User> users = database.showAllUsersInList();
        return new ShowAllUsersResponse(users);
    }
}
