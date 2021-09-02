package lv.javaguru.java2.jg_entertainment.restaurant.console_ui_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.services.ServiceDeleteVisitors;

import java.util.Scanner;

public class UIActionDeleteVisitors implements RestaurantUIAction {
    private ServiceDeleteVisitors deleteVisitors;

    public UIActionDeleteVisitors(ServiceDeleteVisitors deleteVisitors) {
        this.deleteVisitors = deleteVisitors;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write ID visitor's that will be deleted: ");
        Long idVisitors = Long.parseLong(scanner.nextLine());
        deleteVisitors.execute(idVisitors);
        System.out.println("The visitor with ID number " + idVisitors + "-> was deleted from list !");
    }
}
