package lv.javaguru.java2.qwe.core.requests;

public class FindSecurityByNameRequest extends SecurityRequest {

    private final String name;

    public FindSecurityByNameRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}