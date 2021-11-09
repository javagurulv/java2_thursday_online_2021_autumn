package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.core.domain.Bond;
import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.core.requests.data_requests.FilterStocksByMultipleParametersRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Database {

    String addStock(Stock stock);

    String addBond(Bond bond);

    void editStock(Stock stock);

    boolean removeSecurity(String name);

    List<Security> getAllSecurityList();

    Optional<Security> findSecurityByTickerOrName(String name);

    List<Security> filterStocksByMultipleParameters(String sql);

}