package lv.javaguru.java2.qwe.services.user_services;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.database.UserData;
import lv.javaguru.java2.qwe.request.AddUserRequest;
import lv.javaguru.java2.qwe.services.validator.AddUserValidator;

import static lv.javaguru.java2.qwe.Type.*;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.messageDialog;

public class AddUserService {

    private final UserData userData;
    private final AddUserValidator validator;

    public AddUserService(UserData userData, AddUserValidator validator) {
        this.userData = userData;
        this.validator = validator;
    }

    public UserData getUserData() {
        return userData;
    }

    public void execute(AddUserRequest request) {
        if (validator.validate(request).isEmpty()) {
            addUser(request);
            messageDialog("User has been added!");
        }
        else{
            messageDialog("FAILED to add user!\n" +
                    String.join("\n", validator.validate(request)));
        }
    }

    private void addUser(AddUserRequest request) {
        userData.addUser(new User(
                request.getName(),
                Integer.parseInt(request.getAge()),
                valueOf(request.getType()),
                Double.parseDouble(request.getInitialInvestment())
        ));
    }

}