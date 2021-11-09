package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation;

public class AddReservationRequest {

    private String visitorName;
    private String telephoneNumber;
    private String menuTitle;
    private String tableTitle;
    private String reservationDate;

    private String idVisitor;
    private String idTable;
    private String idMenu;

    public AddReservationRequest() {
    }

    public AddReservationRequest(String visitorName,
                                 String telephoneNumber,
                                 String menuTitle,
                                 String tableTitle,
                                 String reservationDate) {
        this.visitorName = visitorName;
        this.telephoneNumber = telephoneNumber;
        this.menuTitle = menuTitle;
        this.tableTitle = tableTitle;
        this.reservationDate = reservationDate;
    }
    // (*new часть
    public AddReservationRequest(String idVisitor,
                                 String idTable,
                                 String idMenu,
                                 String reservationDate) {
        this.idVisitor = idVisitor;
        this.idTable = idTable;
        this.idMenu = idMenu;
        this.reservationDate = reservationDate;
    }//досюда часть новая )

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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setIdVisitor(String idVisitor) {
        this.idVisitor = idVisitor;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public String getIdVisitor() {
        return idVisitor;
    }

    public String getIdTable() {
        return idTable;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
}
