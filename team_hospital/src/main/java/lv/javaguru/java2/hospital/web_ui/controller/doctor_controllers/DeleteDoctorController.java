package lv.javaguru.java2.hospital.web_ui.controller.doctor_controllers;

import lv.javaguru.java2.hospital.doctor.core.requests.DeleteDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.DeleteDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.DeleteDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteDoctorController {

    @Autowired
    private DeleteDoctorService deleteDoctorService;

    @GetMapping(value = "/deleteDoctor")
    public String showDeleteDoctorPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteDoctorRequest());
        return "doctor/deleteDoctor";
    }

    @PostMapping("/deleteDoctor")
    public String processDeleteDoctorRequest(@ModelAttribute(value = "request") DeleteDoctorRequest request, ModelMap modelMap) {
        DeleteDoctorResponse response = deleteDoctorService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else if (!response.hasErrors()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Successfully deleted!");
        }
        return "doctor/deleteDoctor";
    }
}
