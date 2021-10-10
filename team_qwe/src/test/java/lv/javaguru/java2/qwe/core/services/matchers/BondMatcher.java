package lv.javaguru.java2.qwe.core.services.matchers;

import lv.javaguru.java2.qwe.core.domain.Bond;
import org.mockito.ArgumentMatcher;

public class BondMatcher implements ArgumentMatcher<Bond> {

    private final Bond bond;

    public BondMatcher(Bond bond) {
        this.bond = bond;
    }

    @Override
    public boolean matches(Bond argument) {
        return bond.equals(argument);
    }

}