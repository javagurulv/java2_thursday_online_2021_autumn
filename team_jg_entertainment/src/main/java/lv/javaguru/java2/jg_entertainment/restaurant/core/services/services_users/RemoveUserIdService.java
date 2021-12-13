package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.RemoveUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.RemoveUserResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users.RemoveUserIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class RemoveUserIdService {

    @Autowired private UsersRepository usersRepository;
    @Autowired private RemoveUserIdValidator validator;

    public RemoveUserResponse execute(RemoveUserRequest request) {
        List<CoreError> errorList = validator.validate(request);
        if(!errorList.isEmpty()) {
            return new RemoveUserResponse(errorList);
        }
        return usersRepository.getById(request.getId())
                .map(user -> {
                    usersRepository.deleteUserWithID(request.getId());
                    return new RemoveUserResponse(user);
                })
                .orElseGet(() -> {
                    errorList.add(new CoreError("id", "was not found"));
                    return new RemoveUserResponse(errorList);
                });
    }
}
