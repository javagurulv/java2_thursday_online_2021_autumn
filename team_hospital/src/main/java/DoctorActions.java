import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DoctorActions {
    private final Scanner scanner = new Scanner(System.in);

    public void addDoctor(List<Doctor> list) {
        System.out.println("Please enter doctor's name and surname, and speciality: ");
        String[] doctorInfo = scanner.nextLine().split(" ");
        list.add(new Doctor(doctorInfo[0], doctorInfo[1], doctorInfo[2]));
        System.out.println("Doctor " + doctorInfo[0] + " " + doctorInfo[1] + " was successfully added.");
    }

    public void findById(List<Doctor> list) {
        System.out.println("Please enter doctor's id: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (doctorExists(list, id)) {
            for (Doctor d : list) {
                if (d.getId() == id) {
                    System.out.println(d);
                }
            }
        } else {
            findById(list);
        }
    }

    public void deleteById(List<Doctor> list) {
        System.out.println("Please, enter the doctor's id: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (doctorExists(list, id)) {
            list.removeIf(d -> d.getId() == id);
            System.out.println("Doctor with id = " + id + " was successfully deleted.");
        } else {
            deleteById(list);
        }
    }

    public void showAllDoctors(List<Doctor> list) {
        for (Doctor d : list) {
            System.out.println(d);
        }
    }

    public void editDoctor(List<Doctor> list) {
        System.out.print("Please enter doctor's id: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < list.size(); i++) {
            if (doctorExists(list, id)) {
                System.out.println("What information you would like to edit? ");
                System.out.println("1. Name");
                System.out.println("2. Surname");
                System.out.println("3. Speciality");
                int userInput = Integer.parseInt(scanner.nextLine());
                editActions(list, userInput);
            } else {
                editDoctor(list);
            }
        }
    }

    private void editActions(List<Doctor> list, int userInput) {
        for (Doctor doctor : list) {
            switch (userInput) {
                case 1 -> {
                    System.out.println("Please enter new name: ");
                    doctor.setName(scanner.nextLine());
                    System.out.println("The name was successfully changed.");
                }
                case 2 -> {
                    System.out.println("Please enter new surname: ");
                    doctor.setSurname(scanner.nextLine());
                    System.out.println("The surname was successfully changed.");
                }
                case 3 -> {
                    System.out.println("Please enter new speciality: ");
                    doctor.setSpeciality(scanner.nextLine());
                    System.out.println("The speciality was successfully changed.");
                }
            }
        }
    }

    private boolean doctorExists(List<Doctor> list, int id) {
        for (Doctor doctor : list) {
            if (doctor.getId() == id) {
                return true;
            } else {
                System.out.println("There is no doctor with such id. Try again.");
            }
        }
        return false;
    }
}
