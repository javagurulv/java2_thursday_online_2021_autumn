package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MenuListApplication {

   public void execute(ApplicationContext applicationContext){
       ProgramMenuList programList = applicationContext.getBean(ProgramMenuList.class);
       while (true){
           programList.printMenuInRestaurant();
           int numberFromConsole = programList.getMenuNumberFromUser();
           programList.executeSelectMenuItem(numberFromConsole);
       }
   }
}
