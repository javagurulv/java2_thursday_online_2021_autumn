package lv.javaguru.java2.hospital.domain;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class PatientVisit {
	private Doctor doctor;
	private Patient patient;
	private Date visitDate;
	private Long visitID;
	private static final AtomicInteger count = new AtomicInteger(0);

	public PatientVisit(Doctor doctor, Patient patient, Date visitDate) {
		this.doctor = doctor;
		this.patient = patient;
		this.visitDate = visitDate;
		this.visitID = (long) count.incrementAndGet();
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	@Override
	public String toString() {
		return "PatientVisit{" +
				"doctor=" + doctor +
				", patient=" + patient +
				", visitDate=" + visitDate +
				", visitID=" + visitID +
				'}';
	}
}
