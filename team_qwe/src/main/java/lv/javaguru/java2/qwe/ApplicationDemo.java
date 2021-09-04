package lv.javaguru.java2.qwe;

import lv.javaguru.java2.qwe.database.Database;
import lv.javaguru.java2.qwe.database.DatabaseImpl;
import lv.javaguru.java2.qwe.database.UserData;
import lv.javaguru.java2.qwe.database.UserDataImpl;
import lv.javaguru.java2.qwe.ui_actions.ChooseDataMenuUIAction;
import lv.javaguru.java2.qwe.ui_actions.ChooseUserMenuUIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

public class ApplicationDemo {

    public static void main(String[] args) {

        Database database = new DatabaseImpl();
        UserData userData = new UserDataImpl(database);

        //Отдельный поток для симуляции изменения рыночной цены!
        Thread marketPriceSimulator = new Thread(() -> {
            while (true) {
                sleep(5000);
                simulateMarketPrices(database.getSecurityList()); // обновляет рыночную цену в границах +-1% от предыдущей цены
            }
        });
        marketPriceSimulator.start();

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