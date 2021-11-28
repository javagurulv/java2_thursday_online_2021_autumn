package lv.javaguru.java2.oddJobs.web_ui.controllers;

import lv.javaguru.java2.oddJobs.core.requests.add.AddSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.FindSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.get.GetAllSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.add.AddSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.responce.find.FindSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.responce.get.GetAllSpecialistsResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddSpecialistService;
import lv.javaguru.java2.oddJobs.core.services.find.FindSpecialistService;
import lv.javaguru.java2.oddJobs.core.services.get.GetAllSpecialistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindSpecialistController {
    @Autowired private FindSpecialistService findSpecialistService;

    @GetMapping(value = "/findSpecialist")
    public String showFindSpecialists(ModelMap modelMap) {
        FindSpecialistResponse response = findSpecialistService.execute(
                new FindSpecialistRequest()
        );
        modelMap.addAttribute("Specialists", response.getSpecialists());
        return "/findSpecialist";
    }
}
