package lv.javaguru.java2.jg_entertainment.restaurant.console_ui;

public class UIActionExit implements RestaurantUIAction {
    @Override
    public void execute() {
        System.out.println("Thank a lot, have a good day !");
        System.exit(0);
    }
}
