package lv.javaguru.java2.oddJobs.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Advertisements")
public class Advertisement {
    @Id
    @Column(name = "advId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long advId;

    @Column(name = "advTitle", nullable = false)
    private String advTitle;

    @Column(name = "advDescription", nullable = false)
    private String advDescription;

    //private boolean statusIsActive = false;


    public Advertisement(String advTitle, String advDescription) {
        this.advTitle = advTitle;
        this.advDescription = advDescription;
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
