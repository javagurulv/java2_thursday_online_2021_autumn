package lv.javaguru.java2.qwe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Database {

    ArrayList<Security> getSecurityList();

    void importSecurities(String path) throws IOException;

    void addSecurity(String type);

    void removeSecurity(String name);

    void showListOfSecurities(List<Security> list);

    Optional<Security> findSecurityByName(String name);

    List<Security> filterStocksByAnyDoubleParameter(String parameter, String operator, double target);

    List<Security> filterStocksByIndustry(String industry);

}