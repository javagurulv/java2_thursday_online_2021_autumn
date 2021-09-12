package lv.javaguru.java2.jg_entertainment;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.ImplDatabaseTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.TableDatabase;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.AddTableService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.GetAllTablesService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.RemoveTableService;

import java.util.Scanner;

public class TableListApplication {

	private static TableDatabase tableDatabase = new ImplDatabaseTable();
	private static AddTableService addTableService = new AddTableService(tableDatabase);
	private static RemoveTableService removeTableService = new RemoveTableService(tableDatabase);
	private static GetAllTablesService getAllTablesService = new GetAllTablesService(tableDatabase);
	private static UIAction addTableUIAction = new AddTableUIAction(addTableService);
	private static UIAction removeTableUIAction = new RemoveTableUIAction(removeTableService);
	private static UIAction getAllTablesUIAction = new GetAllTablesUIAction(getAllTablesService);
	private static UIAction exitUIAction = new ExitUIAction();

	public static void main(String[] args) {
		while (true) {
			printProgramMenu();
			int menuNumber = getMenuNumberFromUser();
			executeSelectedMenuItem(menuNumber);
		}
	}

	private static void printProgramMenu() {
		System.out.println();
		System.out.println("Program menu:");
		System.out.println("1. Add table to list");
		System.out.println("2. Delete table from list");
		System.out.println("3. Show all tables in the list");
		System.out.println("4. Exit");
		System.out.println();
	}

	private static int getMenuNumberFromUser() {
		System.out.println("Enter menu item number to execute:");
		Scanner scanner = new Scanner(System.in);
		return Integer.parseInt(scanner.nextLine());
	}

	private static void executeSelectedMenuItem(int selectedMenu) {
		switch (selectedMenu) {
			case 1: {
				addTableUIAction.execute();
				break;
			}
			case 2: {
				removeTableUIAction.execute();
				break;
			}
			case 3: {
				getAllTablesUIAction.execute();
				break;
			}
			case 4: {
				exitUIAction.execute();
				break;
			}
		}
	}

}