package lv.javaguru.java2.oddJobs.web_ui.controllers;

import lv.javaguru.java2.oddJobs.core.requests.add.AddAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.responce.add.AddAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddAdvertisementController {

    @Autowired
    private AddAdvertisementService addAdvertisementService;

    @GetMapping(value = "/addAdvertisement")
    public String showAddAdvertisementPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddAdvertisementRequest());
        return "addAdvertisement";
    }

    @PostMapping("/addAdvertisement")
    public String processAddAdvertisementRequest(@ModelAttribute(value = "request") AddAdvertisementRequest request, ModelMap modelMap) {
        AddAdvertisementResponse response = addAdvertisementService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addAdvertisement";
        } else {
            return "redirect:/";
        }
    }
}
