package lv.javaguru.java2.hospital.prescription.core.requests;

public class GetPrescriptionRequest {

    private Long id;

    public GetPrescriptionRequest() {
    }

    public GetPrescriptionRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
