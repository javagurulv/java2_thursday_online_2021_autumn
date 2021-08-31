package doctor;

import java.util.List;
import java.util.Scanner;

public class DoctorActions {

    public void addDoctor(List<Doctor> list) {
        String[] doctorInfo = getUserStringInput();
        list.add(new Doctor(doctorInfo[0], doctorInfo[1], doctorInfo[2]));
        System.out.println("doctor_actions.Doctor " + doctorInfo[0] + " " + doctorInfo[1] + " was successfully added.");
    }

    public void findById(List<Doctor> list) {
        int id = getUserNumericInput("Please enter doctor's id: ");
        if (doctorExists(list, id)) {
            for (Doctor d : list) {
                if (d.getId() == id) {
                    System.out.println(d);
                }
            }
        } else {
            System.out.println("There is no doctor with such id. Try again.");
            findById(list);
        }
    }

    public void deleteById(List<Doctor> list) {
        int id = getUserNumericInput("Please, enter the doctor's id: ");
        if (doctorExists(list, id)) {
            list.removeIf(d -> d.getId() == id);
            System.out.println("doctor_actions.Doctor with id = " + id + " was successfully deleted.");
        } else {
            System.out.println("There is no doctor with such id. Try again.");
            deleteById(list);
        }
    }

    public void showAllDoctors(List<Doctor> list) {
        for (Doctor d : list) {
            System.out.println(d);
        }
    }

    public void editDoctor(List<Doctor> list) {
        int id = getUserNumericInput("Please, enter the doctor's id: ");
        if (doctorExists(list, id)) {
            printEditMenu();
            int userInput = getUserNumericInput("Enter edit menu number: ");
            editActions(list, userInput);
        } else {
            System.out.println("There is no doctor with such id. Try again.");
            editDoctor(list);
        }
    }

    private int getUserNumericInput(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(s);
        return Integer.parseInt(scanner.nextLine());
    }

    private String getUserStringInput(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(s);
        return scanner.nextLine();
    }

    private String[] getUserStringInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter doctor's name and surname, and speciality: ");
        String[] doctorInfo = scanner.nextLine().split(" ");
        return doctorInfo;
    }

    private void printEditMenu() {
        System.out.println("What information you would like to edit? ");
        System.out.println("1. Name");
        System.out.println("2. Surname");
        System.out.println("3. Speciality");
    }

    private void editActions(List<Doctor> list, int userInput) {
        for (Doctor doctor : list) {
            switch (userInput) {
                case 1 -> changeDoctorsName(doctor);
                case 2 -> changeDoctorsSurname(doctor);
                case 3 -> changeDoctorsSpeciality(doctor);
            }
        }
    }

    private void changeDoctorsSpeciality(Doctor doctor) {
        doctor.setSpeciality(getUserStringInput("Please enter new speciality: "));
        System.out.println("The speciality was successfully changed.");
    }

    private void changeDoctorsSurname(Doctor doctor) {
        doctor.setSurname(getUserStringInput("Please enter new surname: "));
        System.out.println("The surname was successfully changed.");
    }

    private void changeDoctorsName(Doctor doctor) {
        doctor.setName(getUserStringInput("Please enter new name: "));
        System.out.println("The name was successfully changed.");
    }

    private boolean doctorExists(List<Doctor> list, int id) {
        for (Doctor doctor : list) {
            if (doctor.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
