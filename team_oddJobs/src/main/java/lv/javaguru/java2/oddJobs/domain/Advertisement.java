package lv.javaguru.java2.oddJobs.domain;

import java.util.Objects;

public class Advertisement {

    private Long advId;
    private String advTitle;
    private String advDescription;
    private boolean statusIsActive = false;

    public Advertisement(String aBoardTitle, String aBoardDescription) {
        this.advTitle = aBoardTitle;
        this.advDescription = aBoardDescription;
    }

    public Advertisement() {

    }

    public Long getAdvId() {
        return advId;
    }

    public String getAdvTitle() {
        return advTitle;
    }

    public String getAdvDescription() {
        return advDescription;
    }

    public void setAdvId(Long advId) {
        this.advId = advId;
    }

    public void setAdvTitle(String advTitle) {
        this.advTitle = advTitle;
    }

    public void setAdvDescription(String advDescription) {
        this.advDescription = advDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return Objects.equals(advId, that.advId) && Objects.equals(advTitle, that.advTitle) && Objects.equals(advDescription, that.advDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(advId, advTitle, advDescription);
    }

    @Override
    public String toString() {
        return "AdvertisementBoard{" +
                "boardId=" + advId +
                ", aBoardTitle='" + advTitle + '\'' +
                ", aBoardDescription='" + advDescription + '\'' +
                '}';
    }
}
