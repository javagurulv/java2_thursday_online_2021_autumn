package lv.javaguru.java2.oddJobs.web_ui.controllers;

import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveAdvertismentRequest;
import lv.javaguru.java2.oddJobs.core.responce.remove.RemoveAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.services.remove.RemoveAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemoveAdvertisementController {
    @Autowired
    private RemoveAdvertisementService removeAdvertisementService;

    @GetMapping(value = "/removeAdvertisement")
    public String showRemoveAdvertisementPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RemoveAdvertismentRequest());
        return "removeAdvertisement";
    }

    @PostMapping("/removeAdvertisement")
    public String processAddAdvertisementRequest(@ModelAttribute(value = "request") RemoveAdvertismentRequest request, ModelMap modelMap) {
        RemoveAdvertisementResponse response = removeAdvertisementService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "removeAdvertisement";
        } else {
            return "redirect:/";
        }
    }
}
