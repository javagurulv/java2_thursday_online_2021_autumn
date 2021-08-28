package lv.javaguru.java2.qwe;

import java.util.List;
import java.util.Optional;

interface UserData {

    List<User> getUserList();

    void generatePortfolio(User user);

    void showListOfUsers(List<User> list);

    Optional<User> findUserByName(String userName);

    void showUserPortfolio(User user);

    void showUserPortfolioGroupedByIndustry(User user);

    void showUserInvestmentsByEachIndustry(User user);

}