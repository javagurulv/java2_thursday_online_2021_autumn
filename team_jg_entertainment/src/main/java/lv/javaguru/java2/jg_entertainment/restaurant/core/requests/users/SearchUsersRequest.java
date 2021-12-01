package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users;

public class SearchUsersRequest {

    private String userName;
    private String usersSurname;
    private Long usersId;
    private String telephoneNumber;

    private Ordering ordering;
    private Paging paging;

    public SearchUsersRequest() {
    }

    public SearchUsersRequest(String userName, String UsersSurname) {
        this.userName = userName;
        this.usersSurname = UsersSurname;
    }

    public SearchUsersRequest(String userName, String UsersSurname, Ordering ordering) {
        this.userName = userName;
        this.usersSurname = UsersSurname;
        this.ordering = ordering;
    }

    public SearchUsersRequest(String userName, String UsersSurname, Paging paging) {
        this.userName = userName;
        this.usersSurname = UsersSurname;
        this.paging = paging;
    }

    public SearchUsersRequest(String userName,
                              String UsersSurname,
                              Ordering ordering,
                              Paging paging) {
        this.userName = userName;
        this.usersSurname = UsersSurname;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getUserName() {
        return userName;
    }

    public String getUsersSurname() {
        return usersSurname;
    }

    public Long getUsersId() {
        return usersId;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public boolean isNameProvided() {
        return this.userName != null
                && !this.userName.isEmpty();
    }

    public boolean isSurnameProvided() {
        return this.usersSurname != null
                && !this.usersSurname.isEmpty();
    }

    public boolean isIDProvided() {
        return this.usersId != null
                && this.usersId != 0;
    }

    public boolean isTelephoneNumberProvided() {
        return this.telephoneNumber != null
                && this.telephoneNumber.isEmpty();
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUsersSurname(String usersSurname) {
        this.usersSurname = usersSurname;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
