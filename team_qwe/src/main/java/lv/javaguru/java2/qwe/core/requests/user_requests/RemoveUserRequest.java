package lv.javaguru.java2.qwe.core.requests.user_requests;

public class RemoveUserRequest {

    private String name;

    public RemoveUserRequest() {}

    public RemoveUserRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}