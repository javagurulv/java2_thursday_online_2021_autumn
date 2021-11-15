package lv.javaguru.java2.jg_entertainment.restaurant.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "reservations")
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @ManyToOne
    @JoinColumn(name = "id_visitor")
    private Visitors visitor;

    @ManyToOne
    @JoinColumn(name = "id_menu")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "id_table")
    private Table table;

    @Column(name = "reservation_date", nullable = false)
    private LocalDateTime reservationDate;

    private String sqlDate;/// просмотреть

    public Reservation() {
    }


    public Reservation(Visitors visitor,
                       Menu menu,
                       Table table,
                       LocalDateTime reservationDate) {
        this.visitor = visitor;
        this.table = table;
        this.menu = menu;
        this.reservationDate = reservationDate;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Visitors getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitors visitor) {
        this.visitor = visitor;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getSqlDate() {
        return sqlDate;
    }

    public void setSqlDate(String sqlDate) {
        this.sqlDate = sqlDate;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "visitor=" + visitor +
                ", table=" + table +
                ", menu=" + menu +
                ", reservationDate=" + reservationDate +
                ", sqlDate='" + sqlDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(idReservation, that.idReservation)
                && Objects.equals(visitor, that.visitor)
                && Objects.equals(table, that.table)
                && Objects.equals(menu, that.menu)
                && Objects.equals(reservationDate, that.reservationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitor, table, menu, reservationDate);
    }

}
