package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionEnum;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.responses.EditPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.validators.EditPrescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class EditPrescriptionService {

    private @Autowired PrescriptionRepository prescriptionRepository;
    private @Autowired PatientRepository patientRepository;
    private @Autowired EditPrescriptionValidator validator;

    public EditPrescriptionResponse execute(EditPrescriptionRequest request){
        List<CoreError> errors = validator.validate(request);

        if(!errors.isEmpty()){
            return new EditPrescriptionResponse(errors);
        }

        // find Prescription
		Optional<Prescription> prescriptionOptional = prescriptionRepository.getById(Long.parseLong(request.getPrescriptionID()));
		if (prescriptionOptional.isPresent()) {
			Prescription prescription = prescriptionOptional.get();

			Long patientId = Long.parseLong(request.getChanges());
			Patient patient = null; // patientRepository.findById(patientId);
			prescription.setPatient(patient);
		}

		// set fields


        return new EditPrescriptionResponse(
				prescriptionRepository.editPrescription(Long.valueOf(request.getPrescriptionID()),
														EditPrescriptionEnum.valueOf(request.getEditPrescriptionEnum()),
														request.getChanges()));
    }
}
