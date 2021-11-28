package lv.javaguru.java2.oddJobs.core.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "specialists_advertisements")
public class SpecialistsAdvertisements {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "specialist_id", nullable = false)
    private Specialist specialist;

    @ManyToOne
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "work_out_date", nullable = false)
    private Date WorkOutDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "work_finish_date")
    private Date workFinishDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public Date getWorkOutDate() {
        return WorkOutDate;
    }

    public void setWorkOutDate(Date workOutDate) {
        WorkOutDate = workOutDate;
    }

    public Date getWorkFinishDate() {
        return workFinishDate;
    }

    public void setWorkFinishDate(Date workFinishDate) {
        this.workFinishDate = workFinishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialistsAdvertisements that = (SpecialistsAdvertisements) o;
        return Objects.equals(id, that.id) && Objects.equals(specialist, that.specialist) && Objects.equals(advertisement, that.advertisement) && Objects.equals(WorkOutDate, that.WorkOutDate) && Objects.equals(workFinishDate, that.workFinishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specialist, advertisement, WorkOutDate, workFinishDate);
    }

    @Override
    public String toString() {
        return "SpecialistsAdvertisements{" +
                "id=" + id +
                ", specialist=" + specialist +
                ", advertisement=" + advertisement +
                ", WorkOutDate=" + WorkOutDate +
                ", workFinishDate=" + workFinishDate +
                '}';
    }
}
