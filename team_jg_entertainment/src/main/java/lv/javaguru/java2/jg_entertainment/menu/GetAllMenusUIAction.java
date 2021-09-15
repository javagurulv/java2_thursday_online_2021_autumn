package lv.javaguru.java2.jg_entertainment.menu;

public class GetAllMenusUIAction implements UIAction {

    private DatabaseMenu database;

    public GetAllMenusUIAction(DatabaseMenu database) {
        this.database = database;
    }
    @Override
    public void execute() {
        System.out.println("Menu list: ");
        database.getAllMenus().forEach(System.out::println);
        System.out.println("Menu list end.");
    }
}