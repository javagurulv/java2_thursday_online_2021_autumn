package lv.javaguru.java2.core.requests.Find;

public class FindAdvertisementByTitleRequest {

    private String title;

    public FindAdvertisementByTitleRequest(String title) {this.title = title;}

    public String getTitle() {return title;}

    public boolean isTitleProvided() {return this.title != null && !this.title.isEmpty();}
}
