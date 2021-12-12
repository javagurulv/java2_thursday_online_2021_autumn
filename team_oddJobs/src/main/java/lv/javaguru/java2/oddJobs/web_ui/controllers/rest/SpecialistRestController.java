package lv.javaguru.java2.oddJobs.web_ui.controllers.rest;

import lv.javaguru.java2.oddJobs.core.requests.add.AddSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.FindSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.get.GetSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.remove.DeleteSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.update.UpdateSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.response.add.AddSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.response.find.FindSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.response.get.GetSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.response.remove.DeleteSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.response.update.UpdateSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddSpecialistService;
import lv.javaguru.java2.oddJobs.core.services.find.FindSpecialistService;
import lv.javaguru.java2.oddJobs.core.services.get.GetSpecialistService;
import lv.javaguru.java2.oddJobs.core.services.remove.DeleteSpecialistService;
import lv.javaguru.java2.oddJobs.core.services.update.UpdateSpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/specialist")
public class SpecialistRestController {
    @Autowired private GetSpecialistService getSpecialistService;
    @Autowired private AddSpecialistService addSpecialistService;
    @Autowired private UpdateSpecialistService specialistService;
    @Autowired private DeleteSpecialistService deleteSpecialistService;
    @Autowired private FindSpecialistService findSpecialistService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetSpecialistResponse getSpecialist(@PathVariable Long id) {
        GetSpecialistRequest request = new GetSpecialistRequest(id);
        return getSpecialistService.execute(request);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddSpecialistResponse addSpecialist(@RequestBody AddSpecialistRequest request) {
        return addSpecialistService.execute(request);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public DeleteSpecialistResponse deleteSpecialist(@PathVariable Long id) {
        DeleteSpecialistRequest request = new DeleteSpecialistRequest(id);
        return deleteSpecialistService.execute(request);
    }

    @PutMapping(path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public UpdateSpecialistResponse updateSpecialist(@RequestBody UpdateSpecialistRequest request) {
        return specialistService.execute(request);
    }

    @PostMapping(path = "/search",
            consumes = "application/json",
            produces = "application/json")
    public FindSpecialistResponse searchSpecialistPost(@RequestBody FindSpecialistRequest request) {
        return findSpecialistService.execute(request);
    }

    @GetMapping(path = "/search", produces = "application/json")
    public FindSpecialistResponse searchSpecialistGet(@RequestParam String specialistName,
                                              @RequestParam String specialistSurname,
                                              @RequestParam String specialistProfession) {
        FindSpecialistRequest request = new FindSpecialistRequest(specialistName,specialistSurname,specialistProfession);
        return findSpecialistService.execute(request);
    }
}
