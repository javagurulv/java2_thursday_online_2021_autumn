package doctor.console_ui;

import doctor.services.AddDoctorService;

import java.util.Scanner;

public class AddDoctorUIAction implements DoctorUIActions {

    private final AddDoctorService addDoctor;

    public AddDoctorUIAction(AddDoctorService addDoctor) {
        this.addDoctor = addDoctor;
    }

    @Override
    public void execute() {
        String[] doctorInfo = getUserStringsInput();
        addDoctor.execute(doctorInfo[0], doctorInfo[1], doctorInfo[2]);
        System.out.println("Doctor " + doctorInfo[0] + " " + doctorInfo[1] + " was successfully added.");
    }

    private String[] getUserStringsInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter doctor's name and surname, and speciality: ");
        return scanner.nextLine().split(" ");
    }
}
