package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.AddDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddDoctorUIAction implements DoctorUIAction {

    @Autowired private AddDoctorService addDoctor;

    @Override
    public void execute() {
        String[] doctorInfo = getUserStringsInput();
        AddDoctorRequest request = new AddDoctorRequest(doctorInfo[0], doctorInfo[1], doctorInfo[2]);
        AddDoctorResponse response = addDoctor.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            System.out.println("New doctor id was: " + response.getNewDoctor().getId());
            System.out.println("Doctor " + doctorInfo[0] + " " + doctorInfo[1] + " was successfully added.");
        }
    }

    private String[] getUserStringsInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter doctor's name and surname, and speciality: ");
        return scanner.nextLine().split(" ");
    }
}
