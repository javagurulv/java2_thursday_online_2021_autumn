package lv.javaguru.java2.hospital.web_ui.controller.prescription_controllers;

import lv.javaguru.java2.hospital.prescription.core.requests.DeletePrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.DeletePrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.DeletePrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeletePrescriptionController {

    @Autowired
    DeletePrescriptionService deletePrescriptionService;

    @GetMapping(value = "/deletePrescription")
    public String showDeletePrescriptionPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeletePrescriptionRequest());
        return "prescription/deletePrescription";
    }

    @PostMapping("/deletePrescription")
    public String processDeletePrescriptionRequest(@ModelAttribute(value = "request") DeletePrescriptionRequest request, ModelMap modelMap) {
        DeletePrescriptionResponse response = deletePrescriptionService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else if (!response.hasErrors()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Successfully deleted!");
        }
        return "prescription/deletePrescription";
    }
}
