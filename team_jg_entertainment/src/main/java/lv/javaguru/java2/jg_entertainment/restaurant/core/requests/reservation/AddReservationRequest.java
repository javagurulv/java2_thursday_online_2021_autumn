package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation;

public class AddReservationRequest {

    private String visitorID;
    private String menuID;
    private String tableID;
    private String reservationDate;

    public AddReservationRequest(String visitorID, String menuID, String tableID, String reservationDate) {
        this.visitorID = visitorID;
        this.menuID = menuID;
        this.tableID = tableID;
        this.reservationDate = reservationDate;
    }

    public String getVisitorID() {
        return visitorID;
    }

    public String getMenuID() {
        return menuID;
    }

    public String getTableID() {
        return tableID;
    }

    public String getReservationDate() {
        return reservationDate;
    }
}
