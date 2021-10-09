package lv.javaguru.java2.qwe;

import lv.javaguru.java2.qwe.config.AppConfiguration;
import lv.javaguru.java2.qwe.ui_actions.AppMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

public class ApplicationDemo {

    private static final ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfiguration.class);

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void main(String[] args) {

        //импортирует в базу данных большой список ценных бумаг
        importData();
        //Симуляция изменения рыночных цен!
        setMarketPriceSimulator(applicationContext);
        //Симуляция изменения текущей даты!
        setDateSimulator(applicationContext);

        AppMenu appMenu = new AppMenu(applicationContext);

        String[] menu = {"DATA MENU", "USER MENU", "EXIT"};

        while (true) {
            String type = inputDialog("Choose operation:", "MAIN MENU", menu);
            appMenu.executeSelectedMainMenuOption(type);
        }

    }

}