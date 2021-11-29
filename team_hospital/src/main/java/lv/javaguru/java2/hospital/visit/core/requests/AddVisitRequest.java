package lv.javaguru.java2.hospital.visit.core.requests;

public class AddVisitRequest {
    private String doctorsID;
    private String patientID;
    private String visitDate;
    private String description;

    public AddVisitRequest(String doctorsID, String patientID, String visitDate) {
        this.doctorsID = doctorsID;
        this.patientID = patientID;
        this.visitDate = visitDate;
    }

    public AddVisitRequest(String doctorsID, String patientID, String visitDate, String description) {
        this.doctorsID = doctorsID;
        this.patientID = patientID;
        this.visitDate = visitDate;
        this.description = description;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getDoctorsID() {
        return doctorsID;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public String getDescription() {
        return description;
    }
}
