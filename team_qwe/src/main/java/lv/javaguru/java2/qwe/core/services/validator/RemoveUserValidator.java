package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.RemoveUserRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
public class RemoveUserValidator {

    @Autowired private UserData userData;

    private final Map<Predicate<RemoveUserRequest>, CoreError> validator = ofEntries(
            entry(this::checkUserPortfolioIsPresent,
                    new CoreError("Name", "can't remove user, because there are securities in the portfolio!")),
            entry(request -> userData.findUserByName(request.getName()).isEmpty(),
                    new CoreError("Name", "No user with such name!"))
    );

    public List<CoreError> validate(RemoveUserRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test(request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    private boolean checkUserPortfolioIsPresent(RemoveUserRequest request) {
        Optional<User> user = userData.findUserByName(request.getName());
        return user.isPresent() && user.get().getPortfolio().size() != 1;
    }

}