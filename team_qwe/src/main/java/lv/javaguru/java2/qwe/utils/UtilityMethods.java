package lv.javaguru.java2.qwe.utils;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.database.UserData;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class UtilityMethods {

    public static String[] convertToStringArray(UserData userData) {
        return userData.getUserList().stream()
                .map(User::getName)
                .toArray(String[]::new);
    }

    public static void simulateMarketPrices(List<Security> list) {
        if (list.size() > 1) {
            IntStream.rangeClosed(0, list.size() - 1)
                    .filter(i -> !list.get(i).getClass().getSimpleName().equals("Cash"))
                    .forEach(i -> list.get(i).setMarketPrice(generateNextPrice(list.get(i).getMarketPrice())));
        }
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public static int convertToInt(double amount) {
        return (int) amount;
    }

    public static double round(double amount) {
        return Math.round(amount * 100.) / 100.;
    }

    private static double generateNextPrice(double currentPrice) {
        return round(currentPrice * (1 + (-1 + (Math.random() * 2)) / 100));
    }

}