package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users;

public class DeleteUserRequest {

    private Long UserId;
    private String userName;

    public DeleteUserRequest(Long UserId, String userName) {
        this.UserId = UserId;
        this.userName = userName;
    }

    public Long getUserId() {
        return UserId;
    }

    public String getUserName() {
        return userName;
    }
}
