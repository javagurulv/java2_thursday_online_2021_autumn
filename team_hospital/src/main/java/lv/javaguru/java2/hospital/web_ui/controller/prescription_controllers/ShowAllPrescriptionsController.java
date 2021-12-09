package lv.javaguru.java2.hospital.web_ui.controller.prescription_controllers;

import lv.javaguru.java2.hospital.prescription.core.requests.ShowAllPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.ShowAllPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.ShowAllPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllPrescriptionsController {

    @Autowired private ShowAllPrescriptionService showAllPrescriptionService;

    @GetMapping(value = "/showAllPrescriptions")
    public String showAllPrescriptions(ModelMap modelMap) {
        ShowAllPrescriptionResponse response = showAllPrescriptionService.execute(
                new ShowAllPrescriptionRequest());
        modelMap.addAttribute("prescriptions", response.getPrescriptions());
        return "prescription/showAllPrescriptions";
    }
}
