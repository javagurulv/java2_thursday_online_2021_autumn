package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.domain.TradeTicket;
import lv.javaguru.java2.qwe.core.domain.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserData {

    LocalDate getCurrentDate();

    Long addUser(User user);

    boolean removeUser(String name);

    List<User> getAllUserList();

    Optional<User> findUserByIdOrName(String userName);

    List<Position> getUserPortfolio(Long userId);

    List<TradeTicket> getUserTrades(Long userId);

    Optional<Double> getUserCash(Long userID);

    void savePosition(Position position, Long userId);

    void saveTradeTicket(TradeTicket ticket);

    Map<String, List<String>> getUserPortfolioGroupedByIndustry(User user);

    Map<String, Double> getUserInvestmentsByEachIndustry(User user);

}