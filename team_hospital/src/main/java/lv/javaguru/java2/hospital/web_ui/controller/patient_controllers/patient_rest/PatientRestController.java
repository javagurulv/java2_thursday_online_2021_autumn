package lv.javaguru.java2.hospital.web_ui.controller.patient_controllers.patient_rest;

import lv.javaguru.java2.hospital.patient.core.requests.*;
import lv.javaguru.java2.hospital.patient.core.responses.*;
import lv.javaguru.java2.hospital.patient.core.services.*;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.SearchPatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientRestController {

    @Autowired private AddPatientService addPatientService;
    @Autowired private FindPatientByIdService findPatientByIdService;
    @Autowired private ShowAllPatientsService showAllPatientsService;
    @Autowired private EditPatientService editPatientService;
    @Autowired private DeletePatientService deletePatientService;
    @Autowired private SearchPatientsService searchPatientsService;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddPatientResponse addPatient(@RequestBody AddPatientRequest request){
        return addPatientService.execute(request);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public FindPatientByIDResponse findPatientByID(@PathVariable String id){
        FindPatientByIdRequest request = new FindPatientByIdRequest(id);
        return findPatientByIdService.execute(request);
    }

    @GetMapping(path = "/", produces = "application/json")
    public ShowAllPatientsResponse showAllPatients(){
        ShowAllPatientsRequest request = new ShowAllPatientsRequest();
        return showAllPatientsService.execute(request);
    }

    @PutMapping(path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public EditPatientResponse editPatient(@RequestBody EditPatientRequest request){
            return editPatientService.execute(request);
    }

    @DeleteMapping(path = "{id}", produces = "application/json")
    public DeletePatientResponse deletePatient(@PathVariable String id){
        return deletePatientService.execute(new DeletePatientRequest(id));
    }

    @PostMapping(path = "/search",
            consumes = "application/json",
            produces = "application/json")
    public SearchPatientsResponse searchPatientsPost(@RequestBody SearchPatientsRequest request){
        return searchPatientsService.execute(request);
    }

    @GetMapping(path = "/search", produces = "application/json")
    public SearchPatientsResponse searchPatientsGet(@RequestParam String name,
                                                    @RequestParam String surname,
                                                    @RequestParam String personalCode){
        return searchPatientsService.execute(new SearchPatientsRequest(name, surname, personalCode));
    }
}
