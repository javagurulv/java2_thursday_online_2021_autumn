package lv.javaguru.java2.qwe.core.services.user_services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.FindUserByNameRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.FindUserByNameResponse;
import lv.javaguru.java2.qwe.core.services.validator.FindUserByNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class FindUserByNameService {

    @Autowired private UserData userData;
    @Autowired private FindUserByNameValidator validator;

    public UserData getUserData() {
        return userData;
    }

    public FindUserByNameResponse execute(FindUserByNameRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<User> result = userData.findUserByIdOrName(request.getUserName());

        if (result.isPresent()) {
            try {
                ObjectMapper o = new ObjectMapper();
                o.registerModule(new JavaTimeModule());
                o.writeValueAsString(result.get());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return (errors.isEmpty() && result.isPresent()) ?
                new FindUserByNameResponse(result.get()) : new FindUserByNameResponse(errors);
    }

}