package lv.javaguru.java2.jg_entertainment.restaurant.domain;

import java.util.Date;
import java.util.Objects;

public class Reservation {

    private Long idReservation;
    private Visitors visitor;
    private Long telephoneNumber;
    private Menu menu;
    private Table table;
    private Date reservationDate;
    private Long idVisitor;
    private Long idTable;
    private Long idMenu;

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

    public Reservation(Long idVisitor, Long idTable, Long idMenu, Long telephoneNumber) {
        this.idVisitor = idVisitor;
        this.idTable = idTable;
        this.idMenu = idMenu;
        this.telephoneNumber = telephoneNumber;
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
                ", idVisitor=" + idVisitor +
                ", idTable=" + idTable +
                ", idMenu=" + idMenu +
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
                && Objects.equals(reservationDate, that.reservationDate)
                && Objects.equals(idVisitor, that.idVisitor)
                && Objects.equals(idTable, that.idTable)
                && Objects.equals(idMenu, that.idMenu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitor, table, menu, telephoneNumber, reservationDate,
                idVisitor, idTable, idMenu);
    }

    public Long getIdVisitor() {
        return idVisitor;
    }

    public void setIdVisitor(Long idVisitor) {
        this.idVisitor = idVisitor;
    }

    public Long getIdTable() {
        return idTable;
    }

    public void setIdTable(Long idTable) {
        this.idTable = idTable;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }
}
