package lv.javaguru.java2.jg_entertainment.restaurant.domain;

import java.util.Date;
import java.util.Objects;

public class Reservation {

    private Visitors visitor;
    private Table table;
    private Menu menu;
    private Long reservationID;
    private Date reservationDate;

    public Reservation(Visitors visitor,
                       Table table,
                       Menu menu,
                       Date reservationDate) {
        this.visitor = visitor;
        this.table = table;
        this.menu = menu;
        this.reservationDate = reservationDate;
    }

    public Reservation(Visitors visitor,
                       Table table,
                       Menu menu,
                       Long reservationID,
                       Date reservationDate) {
        this.visitor = visitor;
        this.table = table;
        this.menu = menu;
        this.reservationID = reservationID;
        this.reservationDate = reservationDate;
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

    public Long getReservationID() {
        return reservationID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(visitor, that.visitor)
                && Objects.equals(table, that.table)
                && Objects.equals(menu, that.menu)
                && Objects.equals(reservationID, that.reservationID)
                && Objects.equals(reservationDate, that.reservationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitor, table, menu, reservationID, reservationDate);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "visitor=" + visitor +
                ", table=" + table +
                ", menu=" + menu +
                ", reservationID=" + reservationID +
                ", reservationDate=" + reservationDate +
                '}';
    }
}
