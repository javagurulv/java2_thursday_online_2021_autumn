package lv.javaguru.java2.qwe.database;

import lv.javaguru.java2.qwe.Bond;
import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.Stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Database {

    ArrayList<Security> getSecurityList();

    void addStock(Stock stock);

    void addBond(Bond bond);

    void removeSecurity(String name);

    void showListOfSecurities(List<Security> list);

    Optional<Security> findSecurityByName(String name);

    List<Security> filterStocksByAnyDoubleParameter(String parameter, String operator, double target);

    List<Security> filterStocksByIndustry(String industry);

}