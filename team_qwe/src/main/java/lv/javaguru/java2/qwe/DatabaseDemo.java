package lv.javaguru.java2.qwe;

import lv.javaguru.java2.qwe.ui_actions.ChooseDataMenuUIAction;
import lv.javaguru.java2.qwe.ui_actions.ChooseUserMenuUIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class DatabaseDemo {

    public static void main(String[] args) {

        Database database = new DatabaseImpl();
        UserData userData = new UserDataImpl(database);

        String[] menu = {"DATA MENU", "USER MENU", "EXIT"};

        while (true) {
            String type = inputDialog("Choose operation:", "MENU", menu);
            switch (type) {
                case "DATA MENU" -> new ChooseDataMenuUIAction(database).execute();
                case "USER MENU" -> new ChooseUserMenuUIAction(userData).execute();
                default -> System.exit(0);
            }
        }

    }

}