package lv.javaguru.java2.hospital.visit.console_ui;

import java.util.Scanner;

public class GetUserInput {

    public int getUserNumericInput(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(s);
        return Integer.parseInt(scanner.nextLine());
    }

    public String getUserStringInput(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(s);
        return scanner.nextLine();
    }

    public Long getUserLongInput(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(s);
        return scanner.nextLong();
    }

}
