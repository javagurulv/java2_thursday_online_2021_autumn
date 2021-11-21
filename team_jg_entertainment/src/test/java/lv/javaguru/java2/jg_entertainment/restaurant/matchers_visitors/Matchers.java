package lv.javaguru.java2.jg_entertainment.restaurant.matchers_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;
import org.mockito.ArgumentMatcher;

public class Matchers implements ArgumentMatcher<User> {

    private String nameVisitor;
    private String surnameVisitor;
    private String telephoneNumber;

    public Matchers(String nameVisitor, String surnameVisitor, String telephoneNumber) {
        this.nameVisitor = nameVisitor;
        this.surnameVisitor = surnameVisitor;
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public boolean matches(User user) {
        return user.getUserName().equals(nameVisitor)
                && user.getSurname().equals(surnameVisitor)
                && user.getTelephoneNumber().equals(telephoneNumber);
    }
}
