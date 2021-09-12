package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestFindVisitorInformation;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseFindVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceFindByIdVisitors;

import java.util.Scanner;

public class UIActionFindVisitors implements RestaurantUIAction {

    private final ServiceFindByIdVisitors findByIdVisitors;

    public UIActionFindVisitors(ServiceFindByIdVisitors findByIdVisitors) {
        this.findByIdVisitors = findByIdVisitors;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter visitor's ID that must be found : ");
        Long idVisitors = Long.parseLong(scanner.nextLine());
        System.out.println("Enter visitor's name that must be found : ");
        String visitorsName = scanner.nextLine();

        RequestFindVisitorInformation request = new RequestFindVisitorInformation(idVisitors, visitorsName);
        ResponseFindVisitors response = findByIdVisitors.execute(request);

        if (response.hasError()) {
            response.getErrorsList().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessageError()));
        } else {
            System.out.println(response.getVisitors());
        }
    }
}
