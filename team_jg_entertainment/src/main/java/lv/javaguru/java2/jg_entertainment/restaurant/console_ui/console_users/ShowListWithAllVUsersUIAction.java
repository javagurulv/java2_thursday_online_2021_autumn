package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.ShowAllUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.ShowAllUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.ShowListUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowListWithAllVUsersUIAction implements UserUIAction {

    @Autowired private ShowListUsersService getAllUsers;

    @Override
    public void execute() {
        System.out.println("Client's list: ");
        ShowAllUsersRequest request = new ShowAllUsersRequest();
        ShowAllUsersResponse response = getAllUsers.execute(request);
        response.getNewUser().forEach(System.out::println);
        System.out.println("Finished! This all list restaurant users !");
    }
}
