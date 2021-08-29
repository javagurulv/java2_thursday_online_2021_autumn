package lv.javaguru.java2.qwe;

import java.util.List;
import java.util.Optional;

public interface UserData {

    List<User> getUserList();

    void addUser();

    void removeUser(String name);

    void generatePortfolio(User user);

    void showListOfUsers(List<User> list);

    Optional<User> findUserByName(String userName);

    void showUserPortfolio(User user);

    void showUserPortfolioGroupedByIndustry(User user);

    void showUserInvestmentsByEachIndustry(User user);

    void showPortfolioSummary(User user);

}