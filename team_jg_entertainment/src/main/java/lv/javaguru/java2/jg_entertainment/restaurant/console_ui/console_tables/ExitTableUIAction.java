package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIComponent;

@DIComponent
public class ExitTableUIAction implements UIAction {

	@Override
	public void execute() {
		System.out.println("Good by!");
		System.exit(0);
	}
}
