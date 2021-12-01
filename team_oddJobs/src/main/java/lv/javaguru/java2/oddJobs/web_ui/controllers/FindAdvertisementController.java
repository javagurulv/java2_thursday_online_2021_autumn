package lv.javaguru.java2.oddJobs.web_ui.controllers;

import lv.javaguru.java2.oddJobs.core.requests.find.FindAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.FindClientsRequest;
import lv.javaguru.java2.oddJobs.core.responce.find.FindAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.responce.find.FindClientsResponse;
import lv.javaguru.java2.oddJobs.core.services.find.FindAdvertisementsService;
import lv.javaguru.java2.oddJobs.core.services.find.FindClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

public class FindAdvertisementController {

    @Autowired
    private FindAdvertisementsService findAdvertisementsService;

    @GetMapping(value = "/findAdvertisement")
    public String showFindAdvertisement(ModelMap modelMap) {
        FindAdvertisementResponse response = findAdvertisementsService.execute(
                new FindAdvertisementRequest()
        );
        modelMap.addAttribute("Advertisements", response.getAdvertisements());
        return "/findAdvertisement";
    }
}
