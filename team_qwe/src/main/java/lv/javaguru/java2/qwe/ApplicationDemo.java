package lv.javaguru.java2.qwe;

import lv.javaguru.java2.qwe.core.API.API;
import lv.javaguru.java2.qwe.ui_actions.AppMenu;
import lv.javaguru.java2.qwe.core.utils.UtilityMethods;
import lv.javaguru.java2.qwe.web_ui.config.SpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class ApplicationDemo {

    private static final ConfigurableApplicationContext applicationContext =
            SpringApplication.run(SpringWebConfiguration.class);

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void main(String[] args) {

/*        //Изменение цен акций в режиме реального времени!
        getAPI().setRealMarketPriceUpdate();
        //Симуляция изменения рыночных цен!
        getUtilityMethods().setMarketPriceSimulator(applicationContext);

        AppMenu appMenu = new AppMenu(applicationContext);

        String[] menu = {"DATA MENU", "USER MENU", "EXIT"};

        while (true) {
            String type = getUtilityMethods().inputDialog("Choose operation:", "MAIN MENU", menu);
            appMenu.executeSelectedMainMenuOption(type);
        }*/

    }

    private static UtilityMethods getUtilityMethods() {
        return applicationContext.getBean(UtilityMethods.class);
    }

    private static API getAPI() {return applicationContext.getBean(API.class);}

}