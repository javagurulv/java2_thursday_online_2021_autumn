import java.util.List;
import java.util.Scanner;

public class DoctorActions {
    private final Scanner scanner = new Scanner(System.in);

    public void addDoctor(List<Doctor> list) {
        System.out.println("Enter doctor name and surname: ");
        String[] nameSurname = scanner.nextLine().split(" ");
        list.add(new Doctor(nameSurname[0], nameSurname[1]));
        System.out.println("Doctor added.");
    }

    public void findById(List<Doctor> list) {
        System.out.println("Enter id: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (Doctor d : list) {
            if (d.getId() == id) {
                System.out.println(d);
            }
        }
    }

    public void deleteById(List<Doctor> list) {
        System.out.println("Enter id: ");
        int id = Integer.parseInt(scanner.nextLine());
        list.removeIf(d -> d.getId() == id);
        System.out.println("Doctor deleted.");
    }

    public void showAllDoctors(List<Doctor> list) {
        for (Doctor d : list) {
            System.out.println(d);
        }
    }
}
