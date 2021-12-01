package lv.javaguru.java2.oddJobs.core.domain;

import java.util.Objects;

public class Account {

    private Client client;
    private Specialist specialist;
    private Advertisement advertisement;


    public Client getClient() {
        return client;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account that = (Account) o;
        return Objects.equals(client, that.client) && Objects.equals(specialist, that.specialist) && Objects.equals(advertisement, that.advertisement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, specialist, advertisement);
    }

    @Override
    public String toString() {
        return "PrivateAccount{" +
                "client=" + client +
                ", specialist=" + specialist +
                ", advertisement=" + advertisement +
                '}';
    }
}
