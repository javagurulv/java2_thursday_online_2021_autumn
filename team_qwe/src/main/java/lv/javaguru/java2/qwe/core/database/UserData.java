package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserData {

    List<User> getUserList();

    Database getDatabase();

    void addUser(User user);

    boolean removeUser(String name);

    List<User> showUserList();

    Optional<User> findUserByName(String userName);

    Map<String, List<String>> showUserPortfolioGroupedByIndustry(User user);

    Map<String, Double> showUserInvestmentsByEachIndustry(User user);

}