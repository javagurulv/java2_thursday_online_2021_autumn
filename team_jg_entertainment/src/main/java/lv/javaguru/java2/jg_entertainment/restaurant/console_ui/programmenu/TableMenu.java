package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programmenu;

import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantListConfiguration;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.ProgramMenuTable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TableMenu implements Menu{
    @Override
    public void execute(ApplicationContext applicationContext) {
        ProgramMenuTable tableProgramMenu = applicationContext.getBean(ProgramMenuTable.class);
        while (true) {
            tableProgramMenu.printTableMenu();
            int tableMenuNumber = tableProgramMenu.getMenuNumberFromUser();
            tableProgramMenu.executeSelectMenuItem(tableMenuNumber);
        }
    }
}
