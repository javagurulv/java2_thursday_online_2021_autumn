package console_ui_visitors;

import service_visitors.ServiceAddAllVisitors;
import service_visitors.ServiceFindByIdVisitors;

import java.util.Scanner;

public class UIActionFindByIdVisitors implements RestaurantUIAction {

    private final ServiceFindByIdVisitors findByIdVisitors;

    public UIActionFindByIdVisitors(ServiceFindByIdVisitors findByIdVisitors) {
        this.findByIdVisitors = findByIdVisitors;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter visitor's ID that must be found : ");
        long idVisitors = Long.parseLong(scanner.nextLine());
        if (findByIdVisitors.execute(idVisitors)) {
            System.out.println(idVisitors + " -> ID client's was found successfully !");
        } else {
            System.out.println("this ID" + idVisitors + " wasn't find in catalogue !");
        }
    }
}
