package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation;

public class AddReservationRequest {

    private String userID;
    private String menuID;
    private String tableID;
    private String reservationDate;

    public AddReservationRequest(String userID, String menuID,
                                 String tableID, String reservationDate) {
        this.userID = userID;
        this.menuID = menuID;
        this.tableID = tableID;
        this.reservationDate = reservationDate;
    }

    public AddReservationRequest() {
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

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
}
