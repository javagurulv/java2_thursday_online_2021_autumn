package lv.javaguru.java2.hardcore.core.services.user;

import lv.javaguru.java2.hardcore.core.database.UserRepository;
import lv.javaguru.java2.hardcore.core.domain.User;

import java.util.List;
import java.util.Optional;

public class UserRepositoryMock implements UserRepository {
    @Override
    public void addUser(User user) {

    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return Optional.empty();
    }

    public void logIn(User user) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

}
