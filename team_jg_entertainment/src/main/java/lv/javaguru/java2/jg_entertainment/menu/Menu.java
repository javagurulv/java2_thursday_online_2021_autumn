package lv.javaguru.java2.jg_entertainment.menu;

import java.util.Objects;

public class Menu {
    private String title;
    private int number;
    private String description;
    private int price;

    public Menu(String title, int number, String description, int price) {
        this.title = title;
        this.number = number;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public int getNumber() {
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

    public void setNumber(int number) {
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
        return number == menu.number &&
                price == menu.price &&
                Objects.equals(title, menu.title) &&
                Objects.equals(description, menu.description);
    }

/*        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Book book = (Book) o;
            return Objects.equals(title, book.title) &&
                    Objects.equals(author, book.author);
        }  }*/



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
