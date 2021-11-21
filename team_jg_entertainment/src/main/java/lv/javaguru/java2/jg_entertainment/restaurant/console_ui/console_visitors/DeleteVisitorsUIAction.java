package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.DeleteVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.DeleteVisitorsResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.DeleteVisitorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteVisitorsUIAction implements VisitorUIAction {

    @Autowired private DeleteVisitorsService deleteVisitors;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write ID user's that will be deleted: ");
        Long idVisitors = Long.parseLong(scanner.nextLine());
        System.out.println("Enter user's name that will be deleted: ");
        String nameVisitor = scanner.nextLine();

        DeleteVisitorRequest request = new DeleteVisitorRequest(idVisitors, nameVisitor);
        DeleteVisitorsResponse deleteVisitorsResponseByID = deleteVisitors.execute(request);

        if (deleteVisitorsResponseByID.hasError()) {
            deleteVisitorsResponseByID.getErrorsList().forEach((coreError ->
                    System.out.println("Error" + coreError.getField() + " " + coreError.getMessageError())));
        } else {
            if (deleteVisitorsResponseByID.ifIdVisitorDelete()) {
                System.out.println("The user with ID number " + idVisitors + ", name " + nameVisitor + "-> was deleted from list !");
            } else {
                System.out.println("Sorry, user's with this ID and name wasn't deleted! Check your information, and try again! ");
            }
        }
    }
}