package lv.javaguru.java2.hospital.web_ui.controller.doctor_controllers.rest;

import lv.javaguru.java2.hospital.doctor.core.requests.*;
import lv.javaguru.java2.hospital.doctor.core.responses.*;
import lv.javaguru.java2.hospital.doctor.core.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorRestController {

    @Autowired private GetDoctorService getDoctorService;
    @Autowired private AddDoctorService addDoctorService;
    @Autowired private ShowAllDoctorsService showAllDoctorsService;
    @Autowired private DeleteDoctorService deleteDoctorService;
    @Autowired private EditDoctorService editDoctorService;
    @Autowired private SearchDoctorsService searchDoctorsService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetDoctorResponse getDoctor(@PathVariable Long id) {
        GetDoctorRequest request = new GetDoctorRequest(id);
        return getDoctorService.execute(request);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddDoctorResponse addDoctor(@RequestBody AddDoctorRequest request) {
        return addDoctorService.execute(request);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public DeleteDoctorResponse deleteDoctor(@PathVariable String id) {
        DeleteDoctorRequest request = new DeleteDoctorRequest(id);
        return deleteDoctorService.execute(request);
    }

    @GetMapping(path = "/", produces = "application/json")
    public ShowAllDoctorsResponse showAllDoctors(){
        ShowAllDoctorsRequest request = new ShowAllDoctorsRequest();
        return showAllDoctorsService.execute(request);
    }

    @PutMapping(path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public EditDoctorResponse editDoctor(@RequestBody EditDoctorRequest request){
        return editDoctorService.execute(request);
    }

    @PostMapping(path = "/search",
            consumes = "application/json",
            produces = "application/json")
    public SearchDoctorsResponse searchDoctorsPost(@RequestBody SearchDoctorsRequest request){
        return searchDoctorsService.execute(request);
    }

    @GetMapping(path = "/search", produces = "application/json")
    public SearchDoctorsResponse searchDoctorsGet(@RequestParam Long id,
                                                  @RequestParam String name,
                                                    @RequestParam String surname,
                                                    @RequestParam String speciality){
        SearchDoctorsRequest request = new SearchDoctorsRequest(id, name, surname, speciality);
        return searchDoctorsService.execute(request);
    }


}
