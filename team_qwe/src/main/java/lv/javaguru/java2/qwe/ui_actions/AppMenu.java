package lv.javaguru.java2.qwe.ui_actions;

import org.springframework.context.ApplicationContext;

import java.util.Map;

public class AppMenu {

    private final Map<String, UIAction> mainMenuMap;

    public AppMenu(ApplicationContext context) {
        mainMenuMap = Map.ofEntries(
                Map.entry("DATA MENU", context.getBean(ChooseDataMenuUIAction.class)),
                Map.entry("USER MENU", context.getBean(ChooseUserMenuUIAction.class)),
                Map.entry("EXIT", context.getBean(ExitUIAction.class)),
                Map.entry("", context.getBean(ExitUIAction.class))
        );
    }

    public void executeSelectedMainMenuOption(String option) {
        mainMenuMap.get(option).execute();
    }

}