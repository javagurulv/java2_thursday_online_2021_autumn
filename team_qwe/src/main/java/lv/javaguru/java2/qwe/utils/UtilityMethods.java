package lv.javaguru.java2.qwe.utils;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
//            System.out.println("Market prices updated!");
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

    public static String[] generateIndustriesArray() {
        return new String[]{"Consumer Staples", "Utilities", "Communications", "Health Care",
                "Technology", "Materials", "Energy", "Financials", "Real Estate",
                "Industrials", "Consumer Discretionary"};
    }

    public static String printErrorList(CoreResponse response) {
        return response.getErrors().stream()
                .map(error -> error.getField() + ": " + error.getMessage())
                .collect(Collectors.joining("\n"));
    }

    public static boolean isNotDouble(String text) {
        try {
            Double.parseDouble(text);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean isNotInteger(String text) {
        try {
            Integer.parseInt(text);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
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