package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.PrescriptionDatabase;
import lv.javaguru.java2.hospital.prescription.core.services.validators.EditPrescriptionValidator;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EditPrescriptionServiceTest {

    @Mock private PrescriptionDatabase database;
    @Mock private EditPrescriptionValidator validator;
    @InjectMocks EditPrescriptionService service;

    @Test
    public void shouldEditPatient(){

    }

}