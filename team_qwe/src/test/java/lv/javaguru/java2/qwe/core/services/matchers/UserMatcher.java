package lv.javaguru.java2.qwe.core.services.matchers;

import lv.javaguru.java2.qwe.core.domain.User;
import org.mockito.ArgumentMatcher;

public class UserMatcher implements ArgumentMatcher<User> {

    private final User user;

    public UserMatcher(User user) {
        this.user = user;
    }

    @Override
    public boolean matches(User argument) {
        return user.equals(argument);
    }

}