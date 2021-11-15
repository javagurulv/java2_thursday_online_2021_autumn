package lv.javaguru.java2.jg_entertainment.restaurant.matchers_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitor;
import org.mockito.ArgumentMatcher;

import java.util.Date;

public class Matchers implements ArgumentMatcher<Reservation> {

    private Visitor visitor;
    private Menu menu;
    private Table table;
    private Date date;

    public Matchers(Visitor visitor, Menu menu, Table table, Date date) {
        this.visitor = visitor;
        this.menu = menu;
        this.table = table;
        this.date = date;
    }

    @Override
    public boolean matches(Reservation reservation) {
        return reservation.getVisitor().equals(visitor)
                && reservation.getMenu().equals(menu)
                && reservation.getTable().equals(table)
                && reservation.getReservationDate().equals(date);
    }
}
