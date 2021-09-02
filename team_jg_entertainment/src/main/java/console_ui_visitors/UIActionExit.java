package console_ui_visitors;

public class UIActionExit implements RestaurantUIAction {
    @Override
    public void execute() {
        System.out.println("Thank a lot, have a good day !");
        System.exit(0);
    }
}
