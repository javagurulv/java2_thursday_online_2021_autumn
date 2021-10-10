package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables;

import org.springframework.stereotype.Component;

@Component
public class ExitTableUIAction implements UIAction {

	@Override
	public void execute() {
		System.out.println("Good by!");
		System.exit(0);
	}
}
