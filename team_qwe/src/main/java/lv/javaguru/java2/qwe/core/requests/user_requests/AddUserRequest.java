package lv.javaguru.java2.qwe.core.requests.user_requests;

public class AddUserRequest {

    private String name;
    private String age;
    private String type;
    private String initialInvestment;

    public AddUserRequest() {}

    public AddUserRequest(String name, String age, String type, String initialInvestment) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.initialInvestment = initialInvestment;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInitialInvestment(String initialInvestment) {
        this.initialInvestment = initialInvestment;
    }

}