package lv.javaguru.java2.jg_entertainment.restaurant.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "tables")
public class Table {

    @Id
    @Column(name = "table_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_title", nullable = false)
    private String title;

    @Column(name = "table_capacity", nullable = false)
    private int tableCapacity;

    @Column(name = "table_price", nullable = false)
    private double price;

    public Table() {
    }

    public Table(String title, int tableCapacity, double price) {
        this.title = title;
        this.tableCapacity = tableCapacity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTableCapacity() {
        return tableCapacity;
    }

    public void setTableCapacity(int tableCapacity) {
        this.tableCapacity = tableCapacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Table table = (Table) o;
        return Objects.equals(id, table.id) &&
                Objects.equals(title, table.title) &&
                Objects.equals(tableCapacity, table.tableCapacity) &&
                Objects.equals(price, table.price);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, tableCapacity, price);
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tableCapacity='" + tableCapacity + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
