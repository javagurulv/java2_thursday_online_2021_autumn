package lv.javaguru.java2.jg_entertainment.restaurant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(name = "menu_title", nullable = false)
    private String title;

    @Column(name = "menu_description", nullable = false)
    private String description;

    @Column(name = "menu_price", nullable = false)
    private double price;

    public Menu() {
    }

    public Menu(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public Long getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return price == menu.price &&
                Objects.equals(title, menu.title) &&
                Objects.equals(number, menu.number) &&
                Objects.equals(description, menu.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, number, description, price);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "title='" + title + '\'' +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
