package lv.javaguru.java2.hospital.web_ui.controller.doctor_controllers;

import lv.javaguru.java2.hospital.doctor.core.requests.ShowAllDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.ShowAllDoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllDoctorsController {

    @Autowired private ShowAllDoctorsService showAllDoctorsService;

    @GetMapping(value = "/showAllDoctors")
    public String showAllDoctors(ModelMap modelMap) {
        ShowAllDoctorsResponse response = showAllDoctorsService.execute(
                new ShowAllDoctorsRequest()
        );
        modelMap.addAttribute("doctors", response.getDoctors());
        return "doctor/showAllDoctors";
    }
}
