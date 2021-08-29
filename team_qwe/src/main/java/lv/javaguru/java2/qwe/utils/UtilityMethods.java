package lv.javaguru.java2.qwe.utils;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.database.UserData;

import javax.swing.*;
import java.util.Optional;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class UtilityMethods {

    public static String[] convertToStringArray(UserData userData) {
        return userData.getUserList().stream()
                .map(User::getName)
                .toArray(String[]::new);
    }

    public static String inputDialog(String text) {
        return Optional.ofNullable(showInputDialog(null, text)).orElse("");
    }

    public static String inputDialog(String request, String title, String[] arr) {
        return Optional.ofNullable((String) showInputDialog(
                null, request,
                title, JOptionPane.QUESTION_MESSAGE, null,
                arr, arr[0])).orElse("");
    }

    public static void messageDialog(String text) {
        showMessageDialog(null, text);
    }

}