package lv.javaguru.java2.qwe.core.requests.user_requests;

public class AddUserRequest {

    private final String id;
    private final String name;
    private final String age;
    private final String type;
    private final String initialInvestment;

    public AddUserRequest(String id, String name, String age, String type, String initialInvestment) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.type = type;
        this.initialInvestment = initialInvestment;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    public String getInitialInvestment() {
        return initialInvestment;
    }

}