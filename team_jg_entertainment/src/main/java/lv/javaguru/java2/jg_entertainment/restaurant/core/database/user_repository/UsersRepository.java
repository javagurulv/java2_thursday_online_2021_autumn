package lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {

    void saveUserToRestaurantList(User clientInfo);

    Optional<User> getById(Long userId);

    List<User> findUsersByNameAndTelephoneNumber(String nameUsers, String telephoneNumber);

    List<User> findUserById(Long userId);

    List<User> findByNameUser(String userName);

    List<User> findBySurnameUser(String userSurname);

    List<User> findByNameAndSurname (String userName, String userSurname);

    boolean deleteUserWithIDAndName(Long id, String userName);

    boolean deleteUserWithID(Long id);

    List<User> showAllUsersInList();

}
