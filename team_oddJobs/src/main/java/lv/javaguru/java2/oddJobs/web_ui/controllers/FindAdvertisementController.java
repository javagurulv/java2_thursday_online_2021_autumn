package lv.javaguru.java2.oddJobs.web_ui.controllers;

import lv.javaguru.java2.oddJobs.core.requests.find.FindAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.response.find.FindAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.services.find.FindAdvertisementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class FindAdvertisementController {

    @Autowired
    private FindAdvertisementsService findAdvertisementsService;

    @GetMapping(value = "/findAdvertisement")
    public String showAdvertisementPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new FindAdvertisementRequest());
        return "findAdvertisement";
    }

    @PostMapping("/findAdvertisement")
    public String processFindAdvRequest(@ModelAttribute(value = "request") FindAdvertisementRequest request, ModelMap modelMap) {
        FindAdvertisementResponse response = findAdvertisementsService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());

        } else if (!response.hasErrors()) {
            modelMap.addAttribute("Advertisements", response.getAdvertisements());
        }
        return "findAdvertisement";

    }
}
