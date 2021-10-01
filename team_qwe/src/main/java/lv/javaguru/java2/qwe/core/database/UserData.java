package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserData {

    List<User> getUserList();

    Database getDatabase();

    LocalDate getCurrentDate();

    void setCurrentDate(LocalDate currentDate);

    void addUser(User user);

    boolean removeUser(String name);

    List<User> getAllUserList();

    Optional<User> findUserByName(String userName);

    Map<String, List<String>> getUserPortfolioGroupedByIndustry(User user);

    Map<String, Double> getUserInvestmentsByEachIndustry(User user);

}