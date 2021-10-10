package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestDeleteVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseDeleteVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceDeleteVisitors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UIActionDeleteVisitors implements RestaurantUIAction {

    @Autowired
    private ServiceDeleteVisitors deleteVisitors;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write ID visitor's that will be deleted: ");
        Long idVisitors = Long.parseLong(scanner.nextLine());
        System.out.println("Enter visitor's name that will be deleted: ");
        String nameVisitor = scanner.nextLine();

        RequestDeleteVisitor request = new RequestDeleteVisitor(idVisitors, nameVisitor);
        ResponseDeleteVisitors responseDeleteVisitorsByID = deleteVisitors.execute(request);

        if (responseDeleteVisitorsByID.hasError()) {
            responseDeleteVisitorsByID.getErrorsList().forEach((coreError ->
                    System.out.println("Error" + coreError.getField() + " " + coreError.getMessageError())));
        } else {
            if (responseDeleteVisitorsByID.ifIdVisitorDelete()) {
                System.out.println("The visitor with ID number " + idVisitors + ", name " + nameVisitor + "-> was deleted from list !");
            } else {
                System.out.println("Sorry, visitor's with this ID and name wasn't deleted! Check your information, and try again! ");
            }
        }
    }
}
// System.out.println("Visitors ID = " + response.getNewVisitor().getIdClient());
//            System.out.println("Visitor: " + visitorsName + " " + visitorsSurname + " is age " + ageVisitor +
//                    ", telephone " + telephoneNumber + "-> was added in restaurant !");