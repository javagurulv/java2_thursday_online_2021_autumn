package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.AddVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseAddVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceAddAllVisitors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddVisitorsUIAction implements RestaurantUIAction {

    @Autowired
    private ServiceAddAllVisitors serviceAddAllVisitors;

    @Override
    public void execute() {
        Scanner scanner = new Scanner((System.in));
        System.out.println("Please, enter visitor's name: ");
        String visitorsName = scanner.nextLine();

        System.out.println("Please, enter visitor's surname: ");
        String visitorsSurname = scanner.nextLine();
///        System.out.println("Please, point visitor's age:");
//        int ageVisitor = scanner.nextInt();
        System.out.println("Enter visitor's telephone number: ");
        Long telephoneNumber = scanner.nextLong();

        AddVisitorRequest request = new AddVisitorRequest(visitorsName, visitorsSurname, telephoneNumber);
        ResponseAddVisitor response = serviceAddAllVisitors.execute(request);

        if (response.hasError()) {
            response.getErrorsList().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessageError()));
        } else {
            System.out.println("Visitors ID = " + response.getNewVisitor().getIdClient());
            System.out.println("Visitor: " + visitorsName + " " + visitorsSurname + //" is age " + ageVisitor +
                    ", telephone " + telephoneNumber + "-> was added in restaurant !");
        }
    }
}