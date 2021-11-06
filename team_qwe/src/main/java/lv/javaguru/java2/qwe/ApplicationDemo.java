package lv.javaguru.java2.qwe;

import lv.javaguru.java2.qwe.config.AppConfiguration;
import lv.javaguru.java2.qwe.ui_actions.AppMenu;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationDemo {

    private static final ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfiguration.class);

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void main(String[] args) {

        //Симуляция изменения рыночных цен!
        getUtilityMethods().setMarketPriceSimulator(applicationContext);
        //Симуляция изменения текущей даты!
//        getUtilityMethods().setDateSimulator(applicationContext);

        AppMenu appMenu = new AppMenu(applicationContext);

        String[] menu = {"DATA MENU", "USER MENU", "EXIT"};

        while (true) {
            String type = getUtilityMethods().inputDialog("Choose operation:", "MAIN MENU", menu);
            appMenu.executeSelectedMainMenuOption(type);
        }

    }

    private static UtilityMethods getUtilityMethods() {
        return applicationContext.getBean(UtilityMethods.class);
    }

}