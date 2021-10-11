package lv.javaguru.java2.jg_entertainment.restaurant.domain;

import java.util.Date;
import java.util.Objects;

public class Reservation {

    private final Visitors visitor;
    private Long telephoneNumber;
    private Menu menu;
    private Table table;
    private Date reservationDate;

    public Reservation(Visitors visitor,
                       Menu menu,
                       Table table,
                       Date reservationDate) {
        this.visitor = visitor;
        this.table = table;
        this.menu = menu;
        this.reservationDate = reservationDate;
    }

    public Reservation(Visitors visitor,
                       Long telephoneNumber,
                       Menu menu,
                       Table table,
                       Date reservationDate) {
        this.visitor = visitor;
        this.table = table;
        this.menu = menu;
        this.telephoneNumber = telephoneNumber;
        this.reservationDate = reservationDate;
    }

    public Reservation(Visitors visitor) {
        this.visitor = visitor;
    }

    public Visitors getVisitor() {
        return visitor;
    }

    public Table getTable() {
        return table;
    }

    public Menu getMenu() {
        return menu;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public Long getTelephoneNumber() {
        return telephoneNumber;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "visitor=" + visitor +
                ", table=" + table +
                ", menu=" + menu +
                ", reservationID=" + telephoneNumber +
                ", reservationDate=" + reservationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(visitor, that.visitor)
                && Objects.equals(table, that.table)
                && Objects.equals(menu, that.menu)
                && Objects.equals(telephoneNumber, that.telephoneNumber)
                && Objects.equals(reservationDate, that.reservationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitor, table, menu, telephoneNumber, reservationDate);
    }
}
