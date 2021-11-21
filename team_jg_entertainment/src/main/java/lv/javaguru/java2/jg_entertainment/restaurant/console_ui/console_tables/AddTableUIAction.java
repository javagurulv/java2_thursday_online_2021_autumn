package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.AddTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.AddTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.AddTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddTableUIAction implements UIAction {

    @Autowired private AddTableService addTableService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter table title: ");
        String tableTitle = scanner.nextLine();
        System.out.println("Enter table capacity: ");
        int tableCapacity = scanner.nextInt();
        System.out.println("Enter table price: ");
        double price = scanner.nextDouble();

        AddTableRequest request = new AddTableRequest(tableTitle, tableCapacity, price);
        AddTableResponse response = addTableService.execute(request);

        if (response.hasError()) {
            response.getErrorsList().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField()
                            + " " + coreError.getMessageError()));
        } else {
            System.out.println("New table ID was: " + response.getNewTable().getId());
            System.out.println("Your table: " + tableTitle + ", with capacity: " + tableCapacity + "- was added to list! ");
        }
    }
}