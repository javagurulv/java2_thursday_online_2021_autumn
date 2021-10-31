package lv.javaguru.java2.hospital.visit.core.requests;

public class AddVisitRequest {
    private String patientID;
    private String doctorsID;
    private String visitDate;
    private String description;

    public AddVisitRequest(String patientID, String doctorsID, String visitDate) {
        this.patientID = patientID;
        this.doctorsID = doctorsID;
        this.visitDate = visitDate;
    }

    public AddVisitRequest(String patientID, String doctorsID, String visitDate, String description) {
        this.patientID = patientID;
        this.doctorsID = doctorsID;
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
