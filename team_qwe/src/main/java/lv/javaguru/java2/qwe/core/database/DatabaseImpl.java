package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.*;
import lv.javaguru.java2.qwe.core.requests.data_requests.FilterStocksByMultipleParametersRequest;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@DIComponent
public class DatabaseImpl implements Database {

    private ArrayList<Security> securityList;

    public DatabaseImpl() {
        this.securityList = new ArrayList<>();
        securityList.add(new Cash());
    }

    @Override
    public ArrayList<Security> getSecurityList() {
        return securityList;
    }

    @Override
    public void addStock(Stock stock) {
        securityList.add(stock);
    }

    @Override
    public void addBond(Bond bond) {
        securityList.add(bond);
    }

    @Override
    public boolean removeSecurity(String name) {
        return securityList.removeIf(security -> security.getName().equals(name));
    }

    @Override
    public List<Security> getAllSecurityList() {
        return getSecurityList();
    }

    @Override
    public Optional<Security> findSecurityByName(String name) {
        return securityList.stream()
                .filter(security -> security.getName().equals(name))
                .findAny();
    }

    @Override
    public final List<Security> filterStocksByMultipleParameters(
            List<Security> list, FilterStocksByMultipleParametersRequest request, int i) {
        List<Security> nextList = list;
        nextList = nextList.stream()
                .filter(security -> security.getClass().getSimpleName().equals("Stock"))
                .filter(request.getList().get(i))
                .collect(Collectors.toList());
        i++;
        if (i == request.getList().size()) {
            sortBy(nextList, request);
            return getPage(nextList, request);
        }
        return filterStocksByMultipleParameters(nextList, request, i);
    }

    private void sortBy(List<Security> list, FilterStocksByMultipleParametersRequest request) {
        if (request.getOrderBy() != null && !request.getOrderBy().isEmpty() && request.getOrderDirection().equals("ASCENDING")) {
            list.sort((Comparator<? super Security>) getComparator(request));
        }
        if (request.getOrderBy() != null && !request.getOrderBy().isEmpty() && request.getOrderDirection().equals("DESCENDING")) {
            list.sort((Comparator<? super Security>) getComparator(request).reversed());
        }
    }

    private List<Security> getPage(List<Security> list, FilterStocksByMultipleParametersRequest request) {
        if (request.getPageNumber() != 0) {
            return list.stream()
                    .skip((long) request.getPageSize() * (request.getPageNumber() - 1))
                    .limit(request.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return list;
        }
    }

    private Comparator<? extends Security> getComparator(FilterStocksByMultipleParametersRequest request) {
        Map<String, Comparator<? extends Security>> map = ofEntries(
                entry("Name", Comparator.comparing(Security::getName)),
                entry("Industry", Comparator.comparing(Security::getIndustry)),
                entry("Currency", Comparator.comparing(Security::getCurrency)),
                entry("Market price", Comparator.comparing(Security::getMarketPrice)),
                entry("Dividend", Comparator.comparing(Stock::getDividends)),
                entry("Risk weight", Comparator.comparing(Stock::getRiskWeight))
        );
        return map.entrySet().stream()
                .filter(entry -> entry.getKey().equals(request.getOrderBy()))
                .map(Map.Entry::getValue)
                .findAny().get();
    }

}