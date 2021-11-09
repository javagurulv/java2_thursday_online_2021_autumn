package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TableListApplication {

    public void execute(ApplicationContext applicationContext) {
        ProgramTableList programList = applicationContext.getBean(ProgramTableList.class);
        while (true) {
            programList.printTableMenu();
            int numberFromConsole = programList.getMenuNumberFromUser();
            programList.executeSelectMenuItem(numberFromConsole);
        }
    }
}
