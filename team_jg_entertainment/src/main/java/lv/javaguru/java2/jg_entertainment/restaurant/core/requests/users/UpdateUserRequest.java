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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    public void setNewSurname(String newSurname) {
        this.newSurname = newSurname;
    }

    public void setNewTelephoneNumber(String newTelephoneNumber) {
        this.newTelephoneNumber = newTelephoneNumber;
    }
}
