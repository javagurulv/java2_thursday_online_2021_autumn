package lv.javaguru.java2.oddJobs.console_ui.get;

import lv.javaguru.java2.oddJobs.core.domain.Advertisement;
import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import lv.javaguru.java2.oddJobs.core.requests.get.GetAllAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.requests.get.GetAllClientsRequest;
import lv.javaguru.java2.oddJobs.core.responce.get.GetAllAdvertisementsResponse;
import lv.javaguru.java2.oddJobs.core.responce.get.GetAllClientsResponse;
import lv.javaguru.java2.oddJobs.core.services.get.GetAllAdvertisementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllAdvertisementsUIAction implements UIAction {

   @Autowired
   private GetAllAdvertisementsService getAllAdvertisementsService;


    @Override
    public void execute() {
        System.out.println("Advertisement board: ");
        GetAllAdvertisementRequest request = new GetAllAdvertisementRequest();
        GetAllAdvertisementsResponse response = getAllAdvertisementsService.execute(request);
        response.getAdvertisements().forEach(System.out::println);
        System.out.println("Advertisement board end.");

    }
}
