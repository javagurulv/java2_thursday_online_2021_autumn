package lv.javaguru.java2.hospital.web_ui.controller.doctor_controllers;

import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.EditDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.EditDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditDoctorController {

    @Autowired private EditDoctorService editDoctorService;

    @GetMapping(value = "/editDoctor")
    public String showEditDoctorPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new EditDoctorRequest());
        return "doctor/editDoctor";
    }

    @PostMapping("/editDoctor")
    public String processEditDoctorRequest(@ModelAttribute(value = "request") EditDoctorRequest request, ModelMap modelMap) {
        EditDoctorResponse response = editDoctorService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else if (!response.hasErrors()) {
            modelMap.addAttribute("errors", null);
            modelMap.addAttribute("message", "Successfully edited!");
        }
        return "doctor/editDoctor";
    }

}
