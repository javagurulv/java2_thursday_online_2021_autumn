package lv.javaguru.java2.qwe;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.dependency_injection.ApplicationContext;
import lv.javaguru.java2.qwe.dependency_injection.DIApplicationContextBuilder;
import lv.javaguru.java2.qwe.ui_actions.ChooseDataMenuUIAction;
import lv.javaguru.java2.qwe.ui_actions.ChooseUserMenuUIAction;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

public class ApplicationDemo {

    private static final ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("lv.javaguru.java2.qwe");

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void main(String[] args) {

        //импортирует в базу данных большой список ценных бумаг
        importData();

        //Симуляция изменения рыночных цен!
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Database database = applicationContext.getBean(Database.class);
        Runnable simulator = () -> simulateMarketPrices(database.getSecurityList());
        scheduledExecutorService.scheduleAtFixedRate(simulator, 1, 1, TimeUnit.SECONDS);

        //Симуляция изменения текущей даты!
        ScheduledExecutorService scheduledExecutorService1 = Executors.newScheduledThreadPool(1);
        UserData userData = applicationContext.getBean(UserData.class);
        Runnable simulator1 = () -> userData.setCurrentDate(userData.getCurrentDate().plusDays(1));
        scheduledExecutorService1.scheduleAtFixedRate(simulator1, 1, 1, TimeUnit.SECONDS);

        String[] menu = {"DATA MENU", "USER MENU", "EXIT"};

        while (true) {
            String type = inputDialog("Choose operation:", "MAIN MENU", menu);
            switch (type) {
                case "DATA MENU" -> {
                    ChooseDataMenuUIAction uiAction = applicationContext.getBean(ChooseDataMenuUIAction.class);
                    uiAction.execute();
                }
                case "USER MENU" -> {
                    ChooseUserMenuUIAction uiAction = applicationContext.getBean(ChooseUserMenuUIAction.class);
                    uiAction.execute();
                }
                default -> System.exit(0);
            }
        }

    }

}