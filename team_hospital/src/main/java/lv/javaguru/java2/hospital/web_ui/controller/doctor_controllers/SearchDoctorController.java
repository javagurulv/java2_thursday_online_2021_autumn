package lv.javaguru.java2.hospital.web_ui.controller.doctor_controllers;

import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.SearchDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.SearchDoctorsService;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchDoctorController {

    @Autowired private SearchDoctorsService searchDoctorsService;

    @GetMapping(value = "/searchDoctor")
    public String showSearchDoctorPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchDoctorsRequest());
        return "doctor/searchDoctor";
    }

    @PostMapping("/searchDoctor")
    public String processSearchDoctorRequest(@ModelAttribute(value = "request") SearchDoctorsRequest request, ModelMap modelMap) {
        SearchDoctorsResponse response = searchDoctorsService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else if (!response.hasErrors()){
            modelMap.addAttribute("doctors", response.getDoctors());
        }
        return "doctor/searchDoctor";
    }
}
