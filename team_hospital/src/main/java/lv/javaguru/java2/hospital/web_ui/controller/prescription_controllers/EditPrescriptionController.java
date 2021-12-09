package lv.javaguru.java2.hospital.web_ui.controller.prescription_controllers;

import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.EditPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.EditPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditPrescriptionController {

    @Autowired private EditPrescriptionService editPrescriptionService;

    @GetMapping(value = "/editPrescription")
    public String showEditPrescriptionPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new EditPrescriptionRequest());
        return "prescription/editPrescription";
    }

    @PostMapping("/editPrescription")
    public String processEditPrescriptionRequest(@ModelAttribute(value = "request") EditPrescriptionRequest request, ModelMap modelMap) {
        EditPrescriptionResponse response = editPrescriptionService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else if (!response.hasErrors()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Successfully edited!");
        }
        return "prescription/editPrescription";
    }
}
