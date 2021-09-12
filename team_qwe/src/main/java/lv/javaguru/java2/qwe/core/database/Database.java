package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.Bond;
import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.Stock;
import lv.javaguru.java2.qwe.core.requests.FilterStockByMultipleParametersRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Database {

    ArrayList<Security> getSecurityList();

    void addStock(Stock stock);

    void addBond(Bond bond);

    boolean removeSecurity(String name);

    List<Security> showListOfSecurities();

    Optional<Security> findSecurityByName(String name);

/*    List<Security> filterStocksByAnyDoubleParameter(String parameter, String operator, double target);

    List<Security> filterStocksByIndustry(String industry);*/

    List<Security> filterStocksByMultipleParameters(List<Security> list,
                                                    FilterStockByMultipleParametersRequest request, int i);

}