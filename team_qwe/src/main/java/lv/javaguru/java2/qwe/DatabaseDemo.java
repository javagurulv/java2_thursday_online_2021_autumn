package lv.javaguru.java2.qwe;

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;

import static lv.javaguru.java2.qwe.DatabaseImpl.inputDialog;
import static lv.javaguru.java2.qwe.DatabaseImpl.messageDialog;

class DatabaseDemo {

    public static void main(String[] args) {

        Database database = new DatabaseImpl();
        executeCase1(database);
        UserData userData = new UserDataImpl(database);

        String[] menu = {"IMPORT DATA FROM FILE", "ADD SECURITY", "REMOVE SECURITY", "SHOW LIST",
                "FIND SECURITY BY NAME", "FILTER SECURITIES(STOCKS) BY ANY DOUBLE PARAMETER",
                "FILTER SECURITIES(STOCKS) BY INDUSTRY", "USER MENU", "EXIT"};

        while (true) {
            String type = inputDialog("Choose operation:", "MENU", menu);
            switch (type) {
                case "IMPORT DATA FROM FILE" -> executeCase1(database);
                case "ADD SECURITY" -> executeCase2(database);
                case "REMOVE SECURITY" -> executeCase3(database);
                case "SHOW LIST" -> executeCase4(database);
                case "FIND SECURITY BY NAME" -> executeCase5(database);
                case "FILTER SECURITIES(STOCKS) BY ANY DOUBLE PARAMETER" -> executeCase6(database);
                case "FILTER SECURITIES(STOCKS) BY INDUSTRY" -> executeCase7(database);
                case "USER MENU" -> executeCase8(userData);
                default -> System.exit(0);
            }
        }

    }

    private static void executeCase1(Database database) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getPath();
            try {
                database.importSecurities(path);
                messageDialog("Data from " + path + " has been imported!");
            } catch (IOException e) {
                System.out.println("ERROR!");
                e.printStackTrace();
            }
        }
    }

    private static void executeCase2(Database database) {
        database.addSecurity(inputDialog(
                "Choose security type:",
                "ADD SECURITY",
                new String[]{"Stock", "Bond"}
        ));
    }

    private static void executeCase3(Database database) {
        database.removeSecurity(inputDialog("Enter name:"));
    }

    private static void executeCase4(Database database) {
        database.showListOfSecurities(database.getSecurityList());
    }

    private static void executeCase5(Database database) {
        System.out.println(database.findSecurityByName(
                inputDialog("Enter name:")
        ) + "\n");
    }

    private static void executeCase6(Database database) {
        String[] operators = {">", ">=", "<", "<=", "="};
        String[] parameters = {"Market price", "Dividend", "Risk weight"};
        try {
            database.showListOfSecurities(database.filterStocksByAnyDoubleParameter(
                    inputDialog("Choose parameter:", "FILTER", parameters),
                    inputDialog("Choose operator:", "FILTER", operators),
                    Double.parseDouble(inputDialog("Enter amount:"))
            ));
        } catch (NumberFormatException e) {
            System.out.println("Wrong data!");
        }
    }

    private static void executeCase7(Database database) {
        database.showListOfSecurities(
                database.filterStocksByIndustry(inputDialog(
                        "Choose industry:",
                        "FILTER",
                        new String[]{"Consumer Staples", "Utilities", "Communications", "Health Care",
                                "Technology", "Materials", "Energy", "Financials", "Real Estate",
                                "Industrials", "Consumer Discretionary"}
                )));
    }

    private static void executeCase8(UserData userData) {
        String[] userMenu = {"ADD NEW USER", "REMOVE USER", "SHOW USER LIST", "FIND USER BY NAME",
                "GENERATE PORTFOLIO FOR USER", "SHOW USER PORTFOLIO", "SHOW PORTFOLIO SUMMARY", "RETURN TO MAIN MENU"};

        boolean userMenuOpen = true;
        while (userMenuOpen) {
            String type = inputDialog("Choose operation", "USER MENU", userMenu);
            switch (type) {
                case "ADD NEW USER" -> executeUserCase1(userData);
                case "REMOVE USER" -> executeUserCase2(userData);
                case "SHOW USER LIST" -> executeUserCase3(userData);
                case "FIND USER BY NAME" -> executeUserCase4(userData);
                case "GENERATE PORTFOLIO FOR USER" -> executeUserCase5(userData);
                case "SHOW USER PORTFOLIO" -> executeUserCase6(userData);
                case "SHOW PORTFOLIO SUMMARY" -> executeUserCase7(userData);
                default -> userMenuOpen = false;
            }
        }
    }

    private static void executeUserCase1(UserData userData) {
        userData.addUser();
    }

    private static void executeUserCase2(UserData userData) {
        userData.removeUser(inputDialog("Enter name:"));
    }

    private static void executeUserCase3(UserData userData) {
        userData.showListOfUsers(userData.getUserList());
    }

    private static void executeUserCase4(UserData userData) {
        System.out.println(userData.findUserByName(inputDialog("Enter name:")));
    }

    private static void executeUserCase5(UserData userData) {
        Optional<User> user = userData.findUserByName(inputDialog("Enter name:"));
        userData.generatePortfolio(user.orElseThrow(
                RuntimeException::new
        ));
    }

    private static void executeUserCase6(UserData userData) {
        Optional<User> user = userData.findUserByName(inputDialog("Enter name:"));
        userData.showUserPortfolio(user.orElseThrow(
                RuntimeException::new
        ));
    }

    private static void executeUserCase7(UserData userData) {
        Optional<User> user = userData.findUserByName(inputDialog("Enter name:"));
        userData.showPortfolioSummary(user.orElseThrow(
                RuntimeException::new
        ));
    }

}