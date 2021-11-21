package lv.javaguru.java2.qwe.core.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

import static java.util.Map.entry;
import static lv.javaguru.java2.qwe.core.domain.Type.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "type", nullable = false)
    private Type type; // общий уровень благосостояния клиента

    @Column(name = "initial_investment", nullable = false)
    private double initialInvestment; //начальная сумма инвестиций

    @Column(name = "cash", nullable = false)
    private double cash; //денежный остаток на счете

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Position> portfolio = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TradeTicket> trades = new ArrayList<>();

    @Column(name = "portfolio_generation_date")
    private LocalDate portfolioGenerationDate;

    @Column(name = "risk_tolerance")
    private int riskTolerance;

    public User() {}

    public User(String name, int age, Type type, double initialInvestment) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.initialInvestment = initialInvestment;
        if (riskTolerance == 0) {calculateRiskTolerance();}
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setInitialInvestment(double initialInvestment) {
        this.initialInvestment = initialInvestment;
    }

    public void setRiskTolerance(int riskTolerance) {
        this.riskTolerance = riskTolerance;
    }

    public List<Position> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(List<Position> portfolio) {
        this.portfolio = portfolio;
    }

    public List<TradeTicket> getTrades() {
        return trades;
    }

    public void setTrades(List<TradeTicket> trades) {
        this.trades = trades;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Type getType() {
        return type;
    }

    public double getInitialInvestment() {
        return initialInvestment;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public int getRiskTolerance() {
        return riskTolerance;
    }

    public LocalDate getPortfolioGenerationDate() {
        return portfolioGenerationDate;
    }

    public void setPortfolioGenerationDate(LocalDate portfolioGenerationDate) {
        this.portfolioGenerationDate = portfolioGenerationDate;
    }
    /*
                    Рассчитывает насколько большой риск клиент готов взять на себя.
                    Вычисляется исходя из возраста и благосостояния. Условно, молодой
                    и богатый может позволить себе самые рискованные вложения, а старый
                    и менее состоятельный - самые низкорискованные
                     */
    private void calculateRiskTolerance() {
        Map<Predicate<Integer>, Integer> mapAge = Map.ofEntries(
                entry(age -> age >= 16 && age < 30, 5), // чем моложе, тем выше показатель толерантности к риску!
                entry(age -> age >= 30 && age < 40, 4),
                entry(age -> age >= 40 && age < 50, 3),
                entry(age -> age >= 50 && age < 60, 2),
                entry(age -> age >= 60, 1)
        );
        Map<Type, Integer> mapWealth = Map.ofEntries(
                entry(SUPER_RICH, 5), // чем богаче, тем выше показатель толерантности к риску!
                entry(WEALTHY, 4),
                entry(UPPER_MIDDLE, 3),
                entry(MIDDLE, 2),
                entry(LOWER_MIDDLE, 1)
        );
        mapAge.forEach((agePredicate, integer) -> {
            if (agePredicate.test(age)) {
                riskTolerance = integer;
            }
        });
        mapWealth.forEach((Type, integer) -> {
            if (Type.equals(type)) {
                riskTolerance = (riskTolerance + integer) / 2; // вычисляет средний показатель
            }
        });
    }

    private static Map<String, Double[]> createDistributionMatrix() {
        return Map.ofEntries(
                entry("Consumer Staples", new Double[]{17.50, 13.50, 9.50, 05.50, 01.50}),
                entry("Utilities", new Double[]{15.00, 12.00, 9.00, 06.00, 03.00}),
                entry("Communications", new Double[]{13.50, 11.25, 9.00, 06.75, 04.50}),
                entry("Health Care", new Double[]{12.00, 10.50, 9.00, 07.50, 06.00}),
                entry("Technology", new Double[]{10.50, 09.75, 9.00, 08.25, 07.50}),
                entry("Materials", new Double[]{09.00, 09.00, 9.00, 09.00, 09.00}),
                entry("Energy", new Double[]{07.50, 08.25, 9.00, 09.75, 10.50}),
                entry("Financials", new Double[]{06.00, 07.50, 9.00, 10.50, 12.00}),
                entry("Real Estate", new Double[]{04.50, 06.75, 9.00, 11.25, 13.50}),
                entry("Industrials", new Double[]{03.00, 06.00, 9.00, 12.00, 15.00}),
                entry("Consumer Discretionary", new Double[]{01.50, 05.50, 9.50, 13.50, 17.50})
        );
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", type=" + type +
                ", initialInvestment=" + initialInvestment +
                ", cash=" + cash +
                ", portfolioGenerationDate=" + portfolioGenerationDate +
                ", riskTolerance=" + riskTolerance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Double.compare(user.initialInvestment, initialInvestment) == 0
                && Objects.equals(name, user.name) && type == user.type && id == user.id;
    }

}