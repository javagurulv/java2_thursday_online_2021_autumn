package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.AddUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.AddUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.AddAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddUsersUIAction implements UserUIAction {

    @Autowired private AddAllUsersService addAllUsersService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner((System.in));
        System.out.println("Please, enter user's name: ");
        String userName = scanner.nextLine();
        System.out.println("Please, enter user's surname: ");
        String userSurname = scanner.nextLine();
        System.out.println("Enter user's telephone number: ");
        String telephoneNumber = scanner.nextLine();

        AddUserRequest request = new AddUserRequest(userName, userSurname, telephoneNumber);
        AddUsersResponse response = addAllUsersService.execute(request);

        if (response.hasError()) {
            response.getErrorsList().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessageError()));
        } else {
            System.out.println("Users ID = " + response.getNewUser().getUserId());
            System.out.println("User: " + userName + " " + userSurname +
                    ", telephone " + telephoneNumber + "-> was added in restaurant !");
        }
    }
}