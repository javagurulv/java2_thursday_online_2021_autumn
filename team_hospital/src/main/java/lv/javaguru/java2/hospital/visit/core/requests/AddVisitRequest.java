package lv.javaguru.java2.hospital.visit.core.requests;

public class AddVisitRequest {
    private String patientsPersonalCode;
    private String doctorsName;
    private String doctorsSurname;
    private String visitDate;

    public AddVisitRequest(String patientsPersonalCode, String doctorsName,
                           String doctorsSurname, String visitDate) {
        this.patientsPersonalCode = patientsPersonalCode;
        this.doctorsName = doctorsName;
        this.doctorsSurname = doctorsSurname;
        this.visitDate = visitDate;
    }

    public String getPatientsPersonalCode() {
        return patientsPersonalCode;
    }

    public String getDoctorsName() {
        return doctorsName;
    }

    public String getDoctorsSurname() {
        return doctorsSurname;
    }

    public String getVisitDate() {
        return visitDate;
    }
}