import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class PatientActions {
    private final Scanner scanner = new Scanner(System.in);

    public void addPatient(List<Patient> list) {
        System.out.println("Please enter patient's name and surname, and personal code: ");
        String[] patientInfo = scanner.nextLine().split(" ");
        list.add(new Patient(patientInfo[0], patientInfo[1], patientInfo[2]));
        System.out.println("Patient " + patientInfo[0] + " " + patientInfo[1] + " was successfully added.");
    }

    public void findById(List<Patient> list) {
        System.out.println("Please enter the patient's id: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (patientExists(list, id)) {
            for (Patient p : list) {
                if (p.getId() == id) {
                    System.out.println(p);
                }
            }
        } else {
            findById(list);
        }
    }

    public void deleteById(List<Patient> list) {
        System.out.println("Please, enter patient's id: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (patientExists(list, id)) {
            list.removeIf(p -> p.getId() == id);
            System.out.println("Patient with id = " + id + " was successfully deleted.");
        } else {
            deleteById(list);
        }
    }

    public void showAllPatients(List<Patient> list) {
        for (Patient p : list) {
            System.out.println(p);
        }
    }

    public void editPatient(List<Patient> list) {
        System.out.print("Please enter patient's id: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < list.size(); i++) {
            if (patientExists(list, id)) {
                System.out.println("What information you would like to edit? ");
                System.out.println("1. Name");
                System.out.println("2. Surname");
                System.out.println("3. Discharge the patient today");
                int userInput = Integer.parseInt(scanner.nextLine());
                editActions(list, userInput);
            } else {
                editPatient(list);
            }
        }
    }

    private void editActions(List<Patient> list, int userInput) {
        for (Patient patient : list) {
            switch (userInput) {
                case 1 -> {
                    System.out.println("Please enter new name: ");
                    patient.setName(scanner.nextLine());
                    System.out.println("The name was successfully changed.");
                }
                case 2 -> {
                    System.out.println("Please enter new surname: ");
                    patient.setSurname(scanner.nextLine());
                    System.out.println("The surname was successfully changed.");
                }
                case 3 -> {
                    patient.setDateOut(LocalDate.now());
                    System.out.println("The patient was discharge today.");
                }
            }
        }
    }

    private boolean patientExists(List<Patient> list, int id) {
        for (Patient patient : list) {
            if (patient.getId() == id) {
                return true;
            } else {
                System.out.println("There is no patient with such id. Try again.");
            }
        }
        return false;
    }
}
