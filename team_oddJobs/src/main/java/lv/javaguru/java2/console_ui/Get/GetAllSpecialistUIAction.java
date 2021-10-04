package lv.javaguru.java2.console_ui.Get;

import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.core.requests.Get.GetAllSpecialistRequest;
import lv.javaguru.java2.core.responce.Get.GetAllSpecialistsResponse;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;
import lv.javaguru.java2.services.Get.GetAllSpecialistsService;

@DIComponent
public class GetAllSpecialistUIAction implements UIAction {

    @DIDependency
    private GetAllSpecialistsService getAllSpecialistsService;


    @Override
    public void execute() {
        System.out.println("Specialists list: ");
        GetAllSpecialistRequest request = new GetAllSpecialistRequest();
        GetAllSpecialistsResponse response = getAllSpecialistsService.execute(request);
        response.getSpecialists().forEach(System.out::println);
        System.out.println("Specialist list end.");

    }
}

