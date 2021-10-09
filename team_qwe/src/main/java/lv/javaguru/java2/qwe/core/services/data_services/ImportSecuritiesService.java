package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.AddStockRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;
import lv.javaguru.java2.qwe.core.services.validator.AddBondValidator;
import lv.javaguru.java2.qwe.core.services.validator.AddSecurityValidator;
import lv.javaguru.java2.qwe.core.services.validator.AddStockValidator;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ImportSecuritiesService {

    @Autowired private Database database;
    @Autowired private AddStockValidator stockValidator;
    @Autowired private AddBondValidator bondValidator;
    @Autowired private UtilityMethods utils;

    public void execute(String path) throws IOException, ArrayIndexOutOfBoundsException {
        importSecurities(path);
    }

    private void importSecurities(String path) throws IOException, ArrayIndexOutOfBoundsException {
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
    }

    private void importStocks(List<String[]> list) {
        List<CoreRequest> requestList = IntStream.rangeClosed(0, list.size() - 1)
                .filter(i -> list.get(i)[0].equals("Stock"))
                .mapToObj(i -> new AddStockRequest(
                        list.get(i)[1], list.get(i)[2], list.get(i)[3],
                        list.get(i)[4], list.get(i)[5], list.get(i)[6]
                ))
                .collect(Collectors.toList());

        List<CoreRequest> errorRequestList = generateErrorRequestList(requestList, stockValidator);

        if (errorRequestList.isEmpty()) {
            importSt(list);
        } else {
            utils.messageDialog("FAILED to add this list!\n" +
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

    private List<CoreRequest> generateErrorRequestList(List<CoreRequest> requestList,
                                                           AddSecurityValidator validator) {
        return IntStream.rangeClosed(0, requestList.size() - 1)
                .mapToObj(requestList::get)
                .filter(request -> !validator.validate(request).isEmpty())
                .collect(Collectors.toList());
    }

}