package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users;

public class UpdateUserRequest {

    private Long userId;
    private String newUserName;
    private String newSurname;
    private String newTelephoneNumber;

    public UpdateUserRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public String getNewUserName() {
        return newUserName;
    }

    public String getNewSurname() {
        return newSurname;
    }

    public String getNewTelephoneNumber() {
        return newTelephoneNumber;
    }
}
