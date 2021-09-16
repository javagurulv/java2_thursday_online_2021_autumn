package lv.javaguru.java2.jg_entertainment;

import java.util.Objects;

public class Menu {
    private String title;
    private Long number;
    private String description;
    private int price;

      public Menu(String title, String description, int price) {
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

    public int getPrice() {
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

    public void setPrice(int price) {
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
