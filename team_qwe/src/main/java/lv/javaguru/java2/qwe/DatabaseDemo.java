package lv.javaguru.java2.qwe;

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;

import static lv.javaguru.java2.qwe.DatabaseImpl.inputDialog;
import static lv.javaguru.java2.qwe.DatabaseImpl.messageDialog;

class DatabaseDemo {

    public static void main(String[] args) {

        Database database = new DatabaseImpl();
        UserData userData = new UserDataImpl(database);

        String[] menu = {"DATA MENU", "USER MENU", "EXIT"};

        while (true) {
            String type = inputDialog("Choose operation:", "MENU", menu);
            switch (type) {
                case "DATA MENU" -> executeMainMenuCase1(database);
                case "USER MENU" -> executeMainMenuCase2(userData);
                default -> System.exit(0);
            }
        }

    }

    private static void executeMainMenuCase1(Database database) {
        String[] userMenu = {"IMPORT DATA FROM FILE", "ADD SECURITY", "REMOVE SECURITY", "SHOW LIST",
                "FIND SECURITY BY NAME", "FILTER SECURITIES(STOCKS) BY ANY DOUBLE PARAMETER",
                "FILTER SECURITIES(STOCKS) BY INDUSTRY", "RETURN TO MAIN MENU"};

        boolean dataMenuOpen = true;
        while (dataMenuOpen) {
            String type = inputDialog("Choose operation", "DATA MENU", userMenu);
            switch (type) {
                case "IMPORT DATA FROM FILE" -> executeDataMenuCase1(database);
                case "ADD SECURITY" -> executeDataMenuCase2(database);
                case "REMOVE SECURITY" -> executeDataMenuCase3(database);
                case "SHOW LIST" -> executeDataMenuCase4(database);
                case "FIND SECURITY BY NAME" -> executeDataMenuCase5(database);
                case "FILTER SECURITIES(STOCKS) BY ANY DOUBLE PARAMETER" -> executeDataMenuCase6(database);
                case "FILTER SECURITIES(STOCKS) BY INDUSTRY" -> executeDataMenuCase7(database);
                default -> dataMenuOpen = false;
            }
        }
    }

    private static void executeMainMenuCase2(UserData userData) {
        String[] userMenu = {"ADD NEW USER", "REMOVE USER", "SHOW USER LIST", "FIND USER BY NAME",
                "GENERATE PORTFOLIO FOR USER", "SHOW USER PORTFOLIO", "SHOW PORTFOLIO SUMMARY", "RETURN TO MAIN MENU"};

        boolean userMenuOpen = true;
        while (userMenuOpen) {
            String type = inputDialog("Choose operation", "USER MENU", userMenu);
            switch (type) {
                case "ADD NEW USER" -> executeUserMenuCase1(userData);
                case "REMOVE USER" -> executeUserMenuCase2(userData);
                case "SHOW USER LIST" -> executeUserMenuCase3(userData);
                case "FIND USER BY NAME" -> executeUserMenuCase4(userData);
                case "GENERATE PORTFOLIO FOR USER" -> executeUserMenuCase5(userData);
                case "SHOW USER PORTFOLIO" -> executeUserMenuCase6(userData);
                case "SHOW PORTFOLIO SUMMARY" -> executeUserMenuCase7(userData);
                default -> userMenuOpen = false;
            }
        }
    }

    private static void executeDataMenuCase1(Database database) {

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

    private static void executeDataMenuCase2(Database database) {
        database.addSecurity(inputDialog(
                "Choose security type:",
                "ADD SECURITY",
                new String[]{"Stock", "Bond"}
        ));
    }

    private static void executeDataMenuCase3(Database database) {
        database.removeSecurity(inputDialog("Enter name:"));
    }

    private static void executeDataMenuCase4(Database database) {
        database.showListOfSecurities(database.getSecurityList());
    }

    private static void executeDataMenuCase5(Database database) {
        System.out.println(database.findSecurityByName(
                inputDialog("Enter name:")
        ) + "\n");
    }

    private static void executeDataMenuCase6(Database database) {
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

    private static void executeDataMenuCase7(Database database) {
        database.showListOfSecurities(
                database.filterStocksByIndustry(inputDialog(
                        "Choose industry:",
                        "FILTER",
                        new String[]{"Consumer Staples", "Utilities", "Communications", "Health Care",
                                "Technology", "Materials", "Energy", "Financials", "Real Estate",
                                "Industrials", "Consumer Discretionary"}
                )));
    }


    private static void executeUserMenuCase1(UserData userData) {
        userData.addUser();
    }

    private static void executeUserMenuCase2(UserData userData) {
        userData.removeUser(inputDialog("Enter name:"));
    }

    private static void executeUserMenuCase3(UserData userData) {
        userData.showListOfUsers(userData.getUserList());
    }

    private static void executeUserMenuCase4(UserData userData) {
        System.out.println(userData.findUserByName(inputDialog("Enter name:")));
    }

    private static void executeUserMenuCase5(UserData userData) {
        Optional<User> user = userData.findUserByName(
                inputDialog("Enter name:", "GENERATE PORTFOLIO", convertToStringArray(userData))
        );
        userData.generatePortfolio(user.orElseThrow(
                RuntimeException::new
        ));
    }

    private static void executeUserMenuCase6(UserData userData) {
        Optional<User> user = userData.findUserByName(
                inputDialog("Choose user:", "SHOW PORTFOLIO", convertToStringArray(userData))
        );
        userData.showUserPortfolio(user.orElseThrow(
                RuntimeException::new
        ));
    }

    private static void executeUserMenuCase7(UserData userData) {
        Optional<User> user = userData.findUserByName(
                inputDialog("Choose user:", "SHOW SUMMARY", convertToStringArray(userData))
        );
        userData.showPortfolioSummary(user.orElseThrow(
                RuntimeException::new
        ));
    }

    private static String[] convertToStringArray(UserData userData) {
        return userData.getUserList().stream()
                .map(User::getName)
                .toArray(String[]::new);
    }

}