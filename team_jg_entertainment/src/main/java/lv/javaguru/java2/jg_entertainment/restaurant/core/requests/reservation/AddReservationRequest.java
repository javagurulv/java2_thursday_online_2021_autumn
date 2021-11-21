package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation;

public class AddReservationRequest {

    private String userID;
    private String menuID;
    private String tableID;
    private String reservationDate;

    public AddReservationRequest(String userID, String menuID, String tableID, String reservationDate) {
        this.userID = userID;
        this.menuID = menuID;
        this.tableID = tableID;
        this.reservationDate = reservationDate;
    }

    public String getUserID() {
        return userID;
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
