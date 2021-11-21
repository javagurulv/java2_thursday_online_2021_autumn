package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.DeleteUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.DeleteUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.DeleteUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteUsersUIAction implements UserUIAction {

    @Autowired private DeleteUsersService deleteUser;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write ID user's that will be deleted: ");
        Long userID = Long.parseLong(scanner.nextLine());
        System.out.println("Enter user's name that will be deleted: ");
        String userName = scanner.nextLine();

        DeleteUserRequest request = new DeleteUserRequest(userID, userName);
        DeleteUsersResponse deleteUsersResponseByID = deleteUser.execute(request);

        if (deleteUsersResponseByID.hasError()) {
            deleteUsersResponseByID.getErrorsList().forEach(( coreError ->
                    System.out.println("Error" + coreError.getField() + " " + coreError.getMessageError())));
        } else {
            if (deleteUsersResponseByID.ifUserIdDeleted()) {
                System.out.println("The user with ID number " + userID + ", name " + userName + "-> was deleted from list !");
            } else {
                System.out.println("Sorry, user's with this ID and name wasn't deleted! Check your information, and try again! ");
            }
        }
    }
}