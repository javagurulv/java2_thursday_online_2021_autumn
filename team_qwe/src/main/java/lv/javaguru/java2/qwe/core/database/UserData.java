package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.User;

import java.util.List;
import java.util.Optional;

public interface UserData {

    List<User> getUserList();

    Database getDatabase();

    void addUser(User user);

    boolean removeUser(String name);

    List<User> showUserList();

    Optional<User> findUserByName(String userName);

    void showUserPortfolioGroupedByIndustry(User user);

    void showUserInvestmentsByEachIndustry(User user);

    void showPortfolioSummary(User user);

}