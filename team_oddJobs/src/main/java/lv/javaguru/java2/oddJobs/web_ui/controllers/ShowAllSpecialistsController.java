package lv.javaguru.java2.oddJobs.web_ui.controllers;

import lv.javaguru.java2.oddJobs.core.requests.get.GetAllSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.get.GetAllSpecialistsResponse;
import lv.javaguru.java2.oddJobs.core.services.get.GetAllSpecialistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllSpecialistsController {
    @Autowired private GetAllSpecialistsService getAllSpecialistsService;

    @GetMapping(value = "/showAllSpecialists")
    public String showAllSpecialists(ModelMap modelMap) {
        GetAllSpecialistsResponse response = getAllSpecialistsService.execute(
                new GetAllSpecialistRequest()
        );
        modelMap.addAttribute("Specialists", response.getSpecialists());
        return "/showAllSpecialists";
    }

}
