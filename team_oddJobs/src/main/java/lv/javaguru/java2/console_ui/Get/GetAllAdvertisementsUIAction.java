package lv.javaguru.java2.console_ui.Get;

import lv.javaguru.java2.Advertisement;
import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.services.Get.GetAllAdvertisementsService;

public class GetAllAdvertisementsUIAction implements UIAction {

    private GetAllAdvertisementsService getAllAdvertisementsService;

    public GetAllAdvertisementsUIAction(GetAllAdvertisementsService getAllAdvertisementsService){
        this.getAllAdvertisementsService = getAllAdvertisementsService;

    }


    @Override
    public void execute() {
        System.out.println("Advertisement board");

        for (Advertisement advertisement : getAllAdvertisementsService.execute()){
            System.out.println(advertisement);
        }
    }
}
