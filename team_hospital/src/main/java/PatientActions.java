import java.util.List;
import java.util.Scanner;

public class PatientActions {
    private final Scanner scanner = new Scanner(System.in);

    public void addPatient(List<Patient> list) {
        System.out.println("Enter patient name and surname: ");
        String[] nameSurname = scanner.nextLine().split(" ");
        list.add(new Patient(nameSurname[0], nameSurname[1]));
        System.out.println("Patient added.");
    }

    public void findById(List<Patient> list) {
        System.out.println("Enter id: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (Patient p : list) {
            if (p.getId() == id) {
                System.out.println(p);
            }
        }
    }

    public void deleteById(List<Patient> list) {
        System.out.println("Enter id: ");
        int id = Integer.parseInt(scanner.nextLine());
        list.removeIf(p -> p.getId() == id);
        System.out.println("Patient deleted.");
    }

    public void showAllPatients(List<Patient> list) {
        for (Patient p : list) {
            System.out.println(p);
        }
    }
}
