package lv.javaguru.java2.jg_entertainment.restaurant.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @ManyToOne
    @JoinColumn(name = "id_visitor")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_menu")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "id_table")
    private Table table;

    @Column(name = "reservation_date", nullable = false)
    private LocalDateTime reservationDate;

      public Reservation() {
    }

    public Reservation(User user,
                       Menu menu,
                       Table table,
                       LocalDateTime reservationDate) {
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Reservation{" +
                " visitor= " + user +
                ", table= " + table +
                ", menu= " + menu +
                ", reservationDate= " + reservationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(idReservation, that.idReservation)
                && Objects.equals(user, that.user)
                && Objects.equals(table, that.table)
                && Objects.equals(menu, that.menu)
                && Objects.equals(reservationDate, that.reservationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, table, menu, reservationDate);
    }

}
