package lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users;

public class SearchUsersRequest {

    private String userName;
    private String UsersSurname;
    private Long UsersId;
    private String telephoneNumber;

    private Ordering ordering;
    private Paging paging;

    public SearchUsersRequest(String userName,
                              String UsersSurname) {
        this.userName = userName;
        this.UsersSurname = UsersSurname;
    }

    public SearchUsersRequest(String userName,
                              String UsersSurname,
                              Ordering ordering) {
        this.userName = userName;
        this.UsersSurname = UsersSurname;
        this.ordering = ordering;
    }

    public SearchUsersRequest(String userName,
                              String UsersSurname,
                              Paging paging) {
        this.userName = userName;
        this.UsersSurname = UsersSurname;
        this.paging = paging;
    }

    public SearchUsersRequest(String userName,
                              String UsersSurname,
                              Ordering ordering,
                              Paging paging) {
        this.userName = userName;
        this.UsersSurname = UsersSurname;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getUserName() {
        return userName;
    }

    public String getUsersSurname() {
        return UsersSurname;
    }

    public Long getUsersId() {
        return UsersId;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public boolean isNameProvided() {
        return this.userName != null
                && !this.userName.isEmpty();
    }

    public boolean isSurnameProvided() {
        return this.UsersSurname != null
                && !this.UsersSurname.isEmpty();
    }

    public boolean isIDProvided() {
        return this.UsersId != null
                && this.UsersId != 0;
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
}
