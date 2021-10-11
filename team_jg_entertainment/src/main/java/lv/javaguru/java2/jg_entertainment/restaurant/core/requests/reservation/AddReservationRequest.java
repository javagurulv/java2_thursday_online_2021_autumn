package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation;

public class AddReservationRequest {

    private String visitorName;
    private Long telephoneNumber;
    private String menuTitle;
    private String tableTitle;
    private String reservationDate;


    public AddReservationRequest(String visitorName,
                                 String menuTitle,
                                 String tableTitle,
                                 String reservationDate) {
        this.visitorName = visitorName;
        this.menuTitle = menuTitle;
        this.tableTitle = tableTitle;
        this.reservationDate = reservationDate;
    }

    public AddReservationRequest(String visitorName,
                                 Long telephoneNumber,
                                 String menuTitle,
                                 String tableTitle,
                                 String reservationDate) {
        this.visitorName = visitorName;
        this.telephoneNumber = telephoneNumber;
        this.menuTitle = menuTitle;
        this.tableTitle = tableTitle;
        this.reservationDate = reservationDate;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public String getTableTitle() {
        return tableTitle;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public Long getTelephoneNumber() {
        return telephoneNumber;
    }
}
