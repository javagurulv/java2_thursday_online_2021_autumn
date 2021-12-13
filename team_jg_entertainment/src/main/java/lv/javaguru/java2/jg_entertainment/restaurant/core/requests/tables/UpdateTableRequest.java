package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables;

public class UpdateTableRequest {

    private Long id;
    private String newTitle;
    private int newTableCapacity;
    private double newPrice;

    public UpdateTableRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public int getNewTableCapacity() {
        return newTableCapacity;
    }

    public void setNewTableCapacity(int newTableCapacity) {
        this.newTableCapacity = newTableCapacity;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }
}
