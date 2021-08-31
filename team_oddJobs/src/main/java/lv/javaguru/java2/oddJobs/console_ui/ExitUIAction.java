package lv.javaguru.java2.oddJobs.console_ui;

public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("See you later, by!");
        System.exit(0);
    }
}
