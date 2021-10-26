package lv.javaguru.java2.jg_entertainment.restaurant.matchers_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;
import org.mockito.ArgumentMatcher;

public class Matchers implements ArgumentMatcher<Visitors> {

    private String nameVisitor;
    private String surnameVisitor;
    private String telephoneNumber;

    public Matchers(String nameVisitor, String surnameVisitor, String telephoneNumber) {
        this.nameVisitor = nameVisitor;
        this.surnameVisitor = surnameVisitor;
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public boolean matches(Visitors visitors) {
        return visitors.getClientName().equals(nameVisitor)
                && visitors.getSurname().equals(surnameVisitor)
                && visitors.getTelephoneNumber().equals(telephoneNumber);
    }
}
