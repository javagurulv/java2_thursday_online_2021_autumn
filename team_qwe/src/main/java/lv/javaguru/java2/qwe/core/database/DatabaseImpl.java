package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.Bond;
import lv.javaguru.java2.qwe.Cash;
import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.Stock;
import lv.javaguru.java2.qwe.core.requests.data_requests.FilterStocksByMultipleParametersRequest;
import lv.javaguru.java2.qwe.core.services.data_services.ImportSecuritiesService;
import lv.javaguru.java2.qwe.core.services.validator.AddBondValidator;
import lv.javaguru.java2.qwe.core.services.validator.AddStockValidator;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class DatabaseImpl implements Database {

    private final ArrayList<Security> securityList;
    private File file;

    public DatabaseImpl(File file) {
        this.securityList = new ArrayList<>();
        this.file = file;
        securityList.add(new Cash());
        importData();  // автоматически импортирует данные в базу
    }

    //конструктор для тестов
    public DatabaseImpl(String s) {
        this.securityList = new ArrayList<>();
        securityList.add(new Cash());
        importDataForTests();
    }

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

    private void importData() {
        file = new File("./team_qwe/src/main/docs/stocks_list_import.txt");
        ImportSecuritiesService service =
                new ImportSecuritiesService(this,
                        new AddStockValidator(this),
                        new AddBondValidator(this));
        try {
            service.execute(file.getPath());
            System.out.println("Data imported to database!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void importDataForTests() {
        file = new File("./src/test/docs/stocks_list_import.txt");
        ImportSecuritiesService service =
                new ImportSecuritiesService(this,
                        new AddStockValidator(this),
                        new AddBondValidator(this));
        try {
            service.execute(file.getPath());
            System.out.println("Data imported to database!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}