package lv.javaguru.java2.hospital;

import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;
import java.util.Locale;

public class PatientEnumChecker {

    public EditPatientEnum validateEnum(String input) {
        EditPatientEnum editPatientEnum = null;
        try {
           editPatientEnum = EditPatientEnum.valueOf(input.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: User choice must be NAME, SURNAME or PERSONAL_CODE!");
            System.out.println();
        }
        return editPatientEnum;
    }
}