package lv.javaguru.java2.qwe;

import lv.javaguru.java2.qwe.ui_actions.ChooseDataMenuUIAction;
import lv.javaguru.java2.qwe.ui_actions.ChooseUserMenuUIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

public class ApplicationDemo {

    private static final ApplicationContext applicationContext = new ApplicationContext();

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void main(String[] args) {

        //Симуляция изменения рыночных цен!
/*        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Runnable simulator = () -> simulateMarketPrices(database.getSecurityList());
        scheduledExecutorService.scheduleAtFixedRate(simulator, 5, 5, TimeUnit.SECONDS);*/

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