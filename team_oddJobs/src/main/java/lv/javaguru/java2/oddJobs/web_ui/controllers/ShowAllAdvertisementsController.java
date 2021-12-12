package lv.javaguru.java2.oddJobs.web_ui.controllers;

import lv.javaguru.java2.oddJobs.core.requests.get.GetAllAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.response.get.GetAllAdvertisementsResponse;
import lv.javaguru.java2.oddJobs.core.services.get.GetAllAdvertisementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllAdvertisementsController {

    @Autowired private GetAllAdvertisementsService getAllAdvertisementsService;


    @GetMapping(value = "/showAllAdvertisements")
    public String showAllAdvertisements(ModelMap modelMap) {
        GetAllAdvertisementsResponse response = getAllAdvertisementsService.execute(
                new GetAllAdvertisementRequest()
        );
        modelMap.addAttribute("Advertisements", response.getAdvertisements());
        return "/showAllAdvertisements";
    }

}