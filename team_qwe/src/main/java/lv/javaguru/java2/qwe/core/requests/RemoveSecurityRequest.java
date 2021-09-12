package lv.javaguru.java2.qwe.core.requests;

public class RemoveSecurityRequest {

    private final String name;

    public RemoveSecurityRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}