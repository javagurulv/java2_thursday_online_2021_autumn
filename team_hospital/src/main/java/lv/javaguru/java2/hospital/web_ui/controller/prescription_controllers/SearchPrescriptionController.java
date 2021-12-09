package lv.javaguru.java2.hospital.web_ui.controller.prescription_controllers;

import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.SearchDoctorsResponse;
import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.SearchPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.SearchPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchPrescriptionController {

    @Autowired private SearchPrescriptionService searchPrescriptionService;

    @GetMapping(value = "/searchPrescription")
    public String showSearchDoctorPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchPrescriptionRequest());
        return "prescription/searchPrescription";
    }

    @PostMapping("/searchPrescription")
    public String processSearchDoctorRequest(@ModelAttribute(value = "request") SearchPrescriptionRequest request, ModelMap modelMap) {
        SearchPrescriptionResponse response = searchPrescriptionService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else if (!response.hasErrors()){
            modelMap.addAttribute("prescriptions", response.getPrescriptions());
        }
        return "prescription/searchPrescription";
    }
}
