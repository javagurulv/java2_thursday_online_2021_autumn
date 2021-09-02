package console_ui_visitors;

import service_visitors.ServiceAddAllVisitors;
import service_visitors.Visitors;

import java.util.Scanner;

public class UIActionAddVisitors implements RestaurantUIAction {

    private ServiceAddAllVisitors serviceAddAllVisitors;

    public UIActionAddVisitors(ServiceAddAllVisitors serviceAddAllVisitors) {
        this.serviceAddAllVisitors = serviceAddAllVisitors;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner((System.in));
        System.out.println("Enter visitor's name: ");
        String visitorsName = scanner.nextLine();
        System.out.println("Please, enter visitor's surname: ");
        String visitorsSurname = scanner.nextLine();
        System.out.println("Please, point your age:");
        String ageVisitor = scanner.nextLine();
        System.out.println("Enter your telephone number: ");
        long telephoneNumber = scanner.nextLong();
        serviceAddAllVisitors.execute(visitorsName, visitorsSurname, ageVisitor, telephoneNumber);
        System.out.println("Visitor: " + visitorsName + visitorsSurname + " is age " + ageVisitor +
                ", telephone " + telephoneNumber + "-> was added in restaurant !");

    }
}
