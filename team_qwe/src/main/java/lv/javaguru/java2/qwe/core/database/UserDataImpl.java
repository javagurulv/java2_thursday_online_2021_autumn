package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.*;
import static lv.javaguru.java2.qwe.Type.*;

@Component
public class UserDataImpl implements UserData {

    private List<User> userList;
    @Autowired
    private Database database;
    private LocalDate currentDate = LocalDate.now();

    public UserDataImpl() {
        this.userList = new ArrayList<>();
        userList.add(new User("Alexander", 25, SUPER_RICH, 1_000_000));
        userList.add(new User("Tatyana", 32, UPPER_MIDDLE, 125_000));
        userList.add(new User("Vladimir", 78, LOWER_MIDDLE, 30_000));
        userList.add(new User("John", 55, MIDDLE, 50_000));
    }

    @Override
    public List<User> getUserList() {
        return userList;
    }

    @Override
    public Database getDatabase() {
        return database;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public void addUser(User user) {
        userList.add(user);
    }

    @Override
    public boolean removeUser(String name) {
        return userList.removeIf(user -> user.getName().equals(name));
    }

    @Override
    public List<User> getAllUserList() {
        return getUserList();
    }

    @Override
    public Optional<User> findUserByName(String userName) {
        return userList.stream()
                .filter(user -> user.getName().equals(userName))
                .findAny();
    }

    @Override
    public Map<String, List<String>> getUserPortfolioGroupedByIndustry(User user) {
        return user.getPortfolio().stream()
                .map(Position::getSecurity)
                .collect(groupingBy(Security::getIndustry, mapping(Security::getName, toList())));
    }

    @Override
    public Map<String, Double> getUserInvestmentsByEachIndustry(User user) {
        return user.getPortfolio().stream()
                .collect(groupingBy(position -> position.getSecurity().getIndustry(),
                        summingDouble(security -> security.getAmount() * security.getPurchasePrice())));
    }

}