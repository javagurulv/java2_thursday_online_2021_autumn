package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus;

public class AddMenuRequest {

    private String title;
    private String description;
    private double price;

    public AddMenuRequest(String title, String description, int price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
