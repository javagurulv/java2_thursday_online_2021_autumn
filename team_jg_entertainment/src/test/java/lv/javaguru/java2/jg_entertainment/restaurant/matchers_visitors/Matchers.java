package lv.javaguru.java2.jg_entertainment.restaurant.matchers_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitor;
import org.mockito.ArgumentMatcher;

public class Matchers implements ArgumentMatcher<Visitor> {

    private String nameVisitor;
    private String surnameVisitor;
    private String telephoneNumber;

    public Matchers(String nameVisitor, String surnameVisitor, String telephoneNumber) {
        this.nameVisitor = nameVisitor;
        this.surnameVisitor = surnameVisitor;
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public boolean matches(Visitor visitor) {
        return visitor.getClientName().equals(nameVisitor)
                && visitor.getSurname().equals(surnameVisitor)
                && visitor.getTelephoneNumber().equals(telephoneNumber);
    }
}
