package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus;

public class AddMenuRequest {

    private String title;
    private String description;
    private double price;

    public AddMenuRequest() {
    }

    public AddMenuRequest(String title, String description, Double price) {
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

    public double getPrice() { return price; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
