package lv.javaguru.java2.qwe.core.requests.user_requests;

public class RemoveUserRequest {

    private final String name;

    public RemoveUserRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}