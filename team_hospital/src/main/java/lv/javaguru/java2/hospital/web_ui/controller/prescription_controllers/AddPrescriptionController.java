package lv.javaguru.java2.hospital.web_ui.controller.prescription_controllers;

import lv.javaguru.java2.hospital.prescription.core.requests.AddPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.AddPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.AddPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddPrescriptionController {

    @Autowired private AddPrescriptionService addPrescriptionService;

    @GetMapping(value = "/addPrescriptionToList")
    public String showAddPrescriptionPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddPrescriptionRequest());
        return "prescription/addPrescriptionToList";
    }

    @PostMapping("/addPrescriptionToList")
    public String processAddPrescriptionRequest(@ModelAttribute(value = "request") AddPrescriptionRequest request, ModelMap modelMap) {
        AddPrescriptionResponse response = addPrescriptionService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        }
        if(!response.hasErrors()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Successfully added!");
        }
        return "prescription/addPrescriptionToList";
    }
}
