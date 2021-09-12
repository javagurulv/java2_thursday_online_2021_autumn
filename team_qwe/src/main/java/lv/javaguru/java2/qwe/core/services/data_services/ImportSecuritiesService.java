package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Stock;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.AddStockRequest;
import lv.javaguru.java2.qwe.core.requests.SecurityRequest;
import lv.javaguru.java2.qwe.core.services.validator.AddBondValidator;
import lv.javaguru.java2.qwe.core.services.validator.AddSecurityValidator;
import lv.javaguru.java2.qwe.core.services.validator.AddStockValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.messageDialog;

public class ImportSecuritiesService {

    private final Database database;
    private final AddStockValidator stockValidator;
    private final AddBondValidator bondValidator;

    public ImportSecuritiesService(Database database, AddStockValidator stockValidator, AddBondValidator bondValidator) {
        this.database = database;
        this.stockValidator = stockValidator;
        this.bondValidator = bondValidator;
    }

    public void execute(String path) throws IOException {
        importSecurities(path);
    }

    private void importSecurities(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path)).stream()
                .skip(1)
                .collect(Collectors.toList());
        importSecurities(lines);
    }

    private void importSecurities(List<String> rawData) {
        List<String[]> list = rawData.stream()
                .map(data -> data.split(","))
                .collect(Collectors.toList());
        importStocks(list);
//        importBonds(list);
    }

    private void importStocks(List<String[]> list) {
        List<SecurityRequest> requestList = IntStream.rangeClosed(0, list.size() - 1)
                .filter(i -> list.get(i)[0].equals("Stock"))
                .mapToObj(i -> new AddStockRequest(
                        list.get(i)[1], list.get(i)[2], list.get(i)[3],
                        list.get(i)[4], list.get(i)[5], list.get(i)[6]
                ))
                .collect(Collectors.toList());

        List<SecurityRequest> errorRequestList = generateErrorRequestList(requestList, stockValidator);

        if (errorRequestList.isEmpty()) {
            importSt(list);
//            messageDialog("Data has been successfully imported!");
        } else {
            messageDialog("FAILED to add this list!\n" +
                    "Validation FAILED!");
            errorRequestList.stream()
                    .map(request -> (AddStockRequest) request)
                    .forEach(request -> System.out.println("Validation failed for: " + request.getName()));
            System.out.println("Validation failed for " + errorRequestList.size() + " securities!");
        }
    }

    private void importSt(List<String[]> list) {
        IntStream.rangeClosed(0, list.size() - 1)
                .filter(i -> list.get(i)[0].equals("Stock"))
                .forEach(i -> database.addStock(new Stock(
                        list.get(i)[1],
                        list.get(i)[2],
                        list.get(i)[3],
                        Double.parseDouble(list.get(i)[4]),
                        Double.parseDouble(list.get(i)[5]),
                        Double.parseDouble(list.get(i)[6])
                )));
    }

    private List<SecurityRequest> generateErrorRequestList(List<SecurityRequest> requestList,
                                                           AddSecurityValidator validator) {
        return IntStream.rangeClosed(0, requestList.size() - 1)
                .mapToObj(requestList::get)
                .filter(request -> !validator.validate(request).isEmpty())
                .collect(Collectors.toList());
    }

    /*    private void importBonds(List<String[]> list) {
        List<SecurityRequest> requestList = IntStream.rangeClosed(0, list.size() - 1)
                .filter(i -> list.get(i)[0].equals("Bond"))
                .mapToObj(i -> new AddBondRequest(
                        list.get(i)[1], list.get(i)[2], list.get(i)[3],
                        list.get(i)[4], list.get(i)[5], list.get(i)[6],
                        list.get(i)[7], list.get(i)[8]
                ))
                .collect(Collectors.toList());

        List<SecurityRequest> errorRequestList = generateErrorRequestList(requestList, bondValidator);

        if (errorRequestList.isEmpty()) {
            importBn(list);
            messageDialog("Data has been successfully imported!");
        } else {
            messageDialog("FAILED to add this list!\n" +
                    "Validation FAILED!");
            errorRequestList.stream()
                    .map(request -> (AddBondRequest) request)
                    .forEach(request -> System.out.println("Validation failed for: " + request.getName()));
            System.out.println("Validation failed for " + errorRequestList.size() + " securities!");
        }
    }*/

    /*    private void importBn(List<String[]> list) {
        IntStream.rangeClosed(0, list.size() - 1)
                .filter(i -> list.get(i)[0].equals("Bond"))
                .forEach(i -> database.addBond(new Bond(
                        list.get(i)[1],
                        list.get(i)[2],
                        list.get(i)[3],
                        Double.parseDouble(list.get(i)[4]),
                        Double.parseDouble(list.get(i)[5]),
                        list.get(i)[6],
                        Integer.parseInt(list.get(i)[7]),
                        list.get(i)[8]
                )));
    }*/

}