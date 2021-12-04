package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.exit;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.UIAction;
import org.springframework.stereotype.Component;

@Component
public class ExitTableUIAction implements UIAction {

	@Override
	public void execute() {
		System.out.println("Thank a lot, have a good day ! Good by!");
		System.exit(0);
	}
}
