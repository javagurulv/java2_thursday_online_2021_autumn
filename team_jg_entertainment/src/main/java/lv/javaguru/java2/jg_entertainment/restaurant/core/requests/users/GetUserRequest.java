package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users;

public class GetUserRequest {

    private Long userId;

    public GetUserRequest() {
    }

    public GetUserRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
