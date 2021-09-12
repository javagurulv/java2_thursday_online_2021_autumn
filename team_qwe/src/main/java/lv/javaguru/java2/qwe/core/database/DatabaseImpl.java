package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.Bond;
import lv.javaguru.java2.qwe.Cash;
import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.Stock;
import lv.javaguru.java2.qwe.core.requests.FilterStockByMultipleParametersRequest;
import lv.javaguru.java2.qwe.core.services.data_services.ImportSecuritiesService;
import lv.javaguru.java2.qwe.core.services.validator.AddBondValidator;
import lv.javaguru.java2.qwe.core.services.validator.AddStockValidator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DatabaseImpl implements Database {

    private final ArrayList<Security> securityList;
    private File file;

    public DatabaseImpl(File file) {
        this.securityList = new ArrayList<>();
        this.file = file;
        securityList.add(new Cash());
        file = new File("./team_qwe/src/main/docs/stocks_list_import.txt"); // автоматически импортирует данные в базу
        ImportSecuritiesService service =
                new ImportSecuritiesService(this, new AddStockValidator(this), new AddBondValidator(this));
        try {
            service.execute(file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public List<Security> showListOfSecurities() {
        return getSecurityList();
    }

    @Override
    public Optional<Security> findSecurityByName(String name) {
        return securityList.stream()
                .filter(security -> security.getName().equals(name))
                .findAny();
    }

    @Override
    public final List<Security> filterStocksByMultipleParameters(List<Security> list,
                                                                 FilterStockByMultipleParametersRequest request, int i) {
        List<Security> nextList = list;
        nextList = nextList.stream()
                .filter(security -> security.getClass().getSimpleName().equals("Stock"))
                .filter(request.getList().get(i))
                .collect(Collectors.toList());
        i++;
        if (i == request.getList().size()) {
            return nextList;
        }
        return filterStocksByMultipleParameters(nextList, request, i);
    }

}