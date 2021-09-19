package lv.javaguru.java2.qwe;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.database.DatabaseImpl;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.database.UserDataImpl;
import lv.javaguru.java2.qwe.ui_actions.ChooseDataMenuUIAction;
import lv.javaguru.java2.qwe.ui_actions.ChooseUserMenuUIAction;
import lv.javaguru.java2.qwe.utils.UtilityMethods;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

public class ApplicationDemo {

    public static void main(String[] args) {

        Database database = new DatabaseImpl(new File(""));
        UserData userData = new UserDataImpl(database);

        //Симуляция изменения рыночных цен!
/*        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Runnable simulator = () -> simulateMarketPrices(database.getSecurityList());
        scheduledExecutorService.scheduleAtFixedRate(simulator, 5, 5, TimeUnit.SECONDS);*/

        String[] menu = {"DATA MENU", "USER MENU", "EXIT"};

        while (true) {
            String type = inputDialog("Choose operation:", "MAIN MENU", menu);
            switch (type) {
                case "DATA MENU" -> new ChooseDataMenuUIAction(database).execute();
                case "USER MENU" -> new ChooseUserMenuUIAction(userData).execute();
                default -> System.exit(0);
            }
        }

    }

}