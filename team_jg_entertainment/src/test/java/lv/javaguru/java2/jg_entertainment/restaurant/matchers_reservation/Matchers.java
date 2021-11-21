package lv.javaguru.java2.jg_entertainment.restaurant.matchers_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;
import org.mockito.ArgumentMatcher;

import java.time.LocalDateTime;

public class Matchers implements ArgumentMatcher<Reservation> {

    private User user;
    private Menu menu;
    private Table table;
    private LocalDateTime date;

    public Matchers(User user, Menu menu, Table table, LocalDateTime date) {
        this.user = user;
        this.menu = menu;
        this.table = table;
        this.date = date;
    }

    @Override
    public boolean matches(Reservation reservation) {
        return reservation.getUser().equals(user)
                && reservation.getMenu().equals(menu)
                && reservation.getTable().equals(table)
                && reservation.getReservationDate().equals(date);
    }
}
