package lv.javaguru.java2.qwe.core.requests.user_requests;

public class FindUserByNameRequest {

    private String userName;

    public FindUserByNameRequest() {}

    public FindUserByNameRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}