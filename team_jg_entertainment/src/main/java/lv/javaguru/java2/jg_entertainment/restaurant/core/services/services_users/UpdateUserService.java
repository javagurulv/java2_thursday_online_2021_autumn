package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.UpdateUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.UpdateUserResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users.UpdateUserRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UpdateUserService {

    @Autowired private UsersRepository usersRepository;
    @Autowired private UpdateUserRequestValidator validator;

    public UpdateUserResponse execute(UpdateUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()) {
            return new UpdateUserResponse(errors);
        }
        return usersRepository.getById(request.getUserId())
                .map(user -> {
                    user.setUserName(request.getNewUserName());
                    user.setSurname(request.getNewSurname());
                    user.setTelephoneNumber(request.getNewTelephoneNumber());
                    return new UpdateUserResponse(user);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "was not found"));
                    return new UpdateUserResponse(errors);
                });
    }
}
