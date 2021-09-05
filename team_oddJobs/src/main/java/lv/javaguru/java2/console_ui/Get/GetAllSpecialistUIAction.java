package lv.javaguru.java2.console_ui.Get;


import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.services.Get.GetAllSpecialistsService;

public class GetAllSpecialistUIAction implements UIAction {

    private GetAllSpecialistsService getAllSpecialistsService;

    public GetAllSpecialistUIAction(GetAllSpecialistsService getAllSpecialistsService) {
        this.getAllSpecialistsService = getAllSpecialistsService;
    }

    @Override
    public void execute() {
        System.out.println("Specialists list: ");
        for (Specialist specialist : getAllSpecialistsService.execute()) {
            System.out.println(specialist);
        }
    }
}
