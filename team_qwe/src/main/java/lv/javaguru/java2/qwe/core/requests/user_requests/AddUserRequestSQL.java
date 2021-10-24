package lv.javaguru.java2.qwe.core.requests.user_requests;

public class AddUserRequestSQL {

    private final String name;
    private final String age;
    private final String type;
    private final String initialInvestment;

    public AddUserRequestSQL(String name, String age, String type, String initialInvestment) {
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

}