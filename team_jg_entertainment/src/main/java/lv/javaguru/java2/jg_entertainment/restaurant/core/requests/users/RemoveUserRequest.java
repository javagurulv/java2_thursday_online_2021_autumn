package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users;

public class RemoveUserRequest {

    private Long id;

    public RemoveUserRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
