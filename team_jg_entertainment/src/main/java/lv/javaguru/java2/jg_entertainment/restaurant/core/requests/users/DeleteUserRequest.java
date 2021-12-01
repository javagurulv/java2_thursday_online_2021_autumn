package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users;

public class DeleteUserRequest {

    private Long userId;
    private String userName;

    public DeleteUserRequest() {
    }

    public DeleteUserRequest(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
