package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ReservationListApplication {

    public void execute(ApplicationContext applicationContext){
        ProgramReservationList programList = applicationContext.getBean(ProgramReservationList.class);
        while (true){
            programList.printReservationMenu();
            int numberFromConsole = programList.getReservationMenuNumberFromUser();
            programList.executeSelectMenuItem(numberFromConsole);
        }
    }
}
