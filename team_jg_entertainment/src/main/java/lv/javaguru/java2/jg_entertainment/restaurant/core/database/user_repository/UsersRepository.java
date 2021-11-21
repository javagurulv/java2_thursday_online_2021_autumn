package lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;

import java.util.List;


public interface UsersRepository {

    void saveUserToRestaurantList(User clientInfo);

    List<User> findUsersByNameAndTelephoneNumber(String nameUsers, String telephoneNumber);

    List<User> findUserById(Long userId);

    List<User> findByNameUser(String userName);

    List<User> findBySurnameUser(String userSurname);

    List<User> findByNameAndSurname (String userName, String userSurname);

    boolean deleteUserWithIDAndName(Long id, String userName);

    List<User> showAllUsersInList();

}
