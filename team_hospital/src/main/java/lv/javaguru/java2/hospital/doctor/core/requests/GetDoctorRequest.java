package lv.javaguru.java2.hospital.doctor.core.requests;

public class GetDoctorRequest {

    private Long id;

    public GetDoctorRequest() {
    }

    public GetDoctorRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
