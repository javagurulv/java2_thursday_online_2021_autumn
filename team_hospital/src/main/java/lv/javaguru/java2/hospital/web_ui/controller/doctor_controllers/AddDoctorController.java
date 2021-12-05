package lv.javaguru.java2.hospital.web_ui.controller.doctor_controllers;

import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.AddDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddDoctorController {

    @Autowired
    private AddDoctorService addDoctorService;

    @GetMapping(value = "/addDoctorToList")
    public String showAddDoctorPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddDoctorRequest());
        return "doctor/addDoctorToList";
    }

    @PostMapping("/addDoctorToList")
    public String processAddBookRequest(@ModelAttribute(value = "request") AddDoctorRequest request, ModelMap modelMap) {
        AddDoctorResponse response = addDoctorService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());

        }
        if(!response.hasErrors()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Successfully added!");
        }
        return "doctor/addDoctorToList";
    }
}
