package lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

//@Component
public class UsersRepositoryImpl implements UsersRepository {

    List<User> userInRestaurant = new ArrayList<>();
    private Long userId = 1L;

    @Override
    public void saveUserToRestaurantList(User userInfo) {
        userInfo.setUserId(userId);
        userId++;
        userInRestaurant.add(userInfo);
    }

    @Override
    public Optional<User> getById(Long userId) {
        return Optional.empty();
    }

    @Override
    public List<User> findUsersByNameAndTelephoneNumber(String userName, String telephoneNumber) {
        return userInRestaurant.stream()
                .filter(users -> users.getUserName().equals(userName))
                .filter(users -> users.getTelephoneNumber().equals(telephoneNumber))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findUserById(Long idVisitors) {
        return userInRestaurant.stream()
                .filter(users -> users.getUserId().equals(idVisitors))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findByNameUser(String userName) {
        return userInRestaurant.stream()
                .filter(users -> users.getUserName().equals(userName))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findBySurnameUser(String surnameVisitor) {
        return userInRestaurant.stream()
                .filter(users -> users.getSurname().equals(surnameVisitor))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findByNameAndSurname(String name, String surname) {
        return userInRestaurant.stream()
                .filter(users -> users.getUserName().equals(name))
                .filter(users -> users.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteUserWithIDAndName(Long id, String userName) {
        boolean visitorDeleteFromRestaurantList = false;
        Optional<User> usersOptional = userInRestaurant.stream()
                .filter(users ->users.getUserName().equals(userName) &&
                        users.getUserId().equals(id))
                .findFirst();
        if (usersOptional.isPresent()) {
            User user = usersOptional.get();
            visitorDeleteFromRestaurantList = userInRestaurant.remove(user);
        }
        return visitorDeleteFromRestaurantList;
    }

    @Override
    public boolean deleteUserWithID(Long id) {
        return false;
    }

    @Override
    public List<User> showAllUsersInList() {
        return userInRestaurant.stream()
                .sorted(Comparator.comparing(User::getUserName).thenComparing(User::getSurname))
                .collect(toList());
    }
}

//@Override
//    public void deleteClientWithId(Long id) {
//        clientInRestaurant.stream()
//                .filter(customer -> customer.getIdClient().equals(id))
//                .findFirst()
//                .ifPresent(customer -> clientInRestaurant.remove(customer));
//}