package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.GetUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.GetUserResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users.GetUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetUserService {

    @Autowired private UsersRepository usersRepository;
    @Autowired private GetUserValidator validator;

    public GetUserResponse execute(GetUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetUserResponse(errors);
        }
        return usersRepository.getById(request.getUserId())
                .map(GetUserResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "was not found!"));
                    return new GetUserResponse(errors);
                });
    }
}
