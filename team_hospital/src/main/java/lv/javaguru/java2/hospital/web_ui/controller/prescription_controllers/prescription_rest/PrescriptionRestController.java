package lv.javaguru.java2.hospital.web_ui.controller.prescription_controllers.prescription_rest;

import lv.javaguru.java2.hospital.prescription.core.requests.*;
import lv.javaguru.java2.hospital.prescription.core.responses.*;
import lv.javaguru.java2.hospital.prescription.core.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prescription")
public class PrescriptionRestController {

    @Autowired private GetPrescriptionService getPrescriptionService;
    @Autowired private AddPrescriptionService addPrescriptionService;
    @Autowired private DeletePrescriptionService deletePrescriptionService;
    @Autowired private EditPrescriptionService editPrescriptionService;
    @Autowired private ShowAllPrescriptionService showAllPrescriptionService;
    @Autowired private SearchPrescriptionService searchPrescriptionService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetPrescriptionResponse getPrescription(@PathVariable Long id) {
        GetPrescriptionRequest request = new GetPrescriptionRequest(id);
        return getPrescriptionService.execute(request);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddPrescriptionResponse addPrescription(@RequestBody AddPrescriptionRequest request) {
        return addPrescriptionService.execute(request);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public DeletePrescriptionResponse deletePrescription(@PathVariable String id) {
        DeletePrescriptionRequest request = new DeletePrescriptionRequest(id);
        return deletePrescriptionService.execute(request);
    }

    @PutMapping(path = "{id}",
            consumes = "application/json",
            produces = "application/json")
    public EditPrescriptionResponse editPrescription(@RequestBody EditPrescriptionRequest request){
        return editPrescriptionService.execute(request);
    }

    @GetMapping(path = "/", produces = "application/json")
    public ShowAllPrescriptionResponse showAllPrescription(){
        ShowAllPrescriptionRequest request = new ShowAllPrescriptionRequest();
        return showAllPrescriptionService.execute(request);
    }

    @PostMapping(path = "/search",
            consumes = "application/json",
            produces = "application/json")
    public SearchPrescriptionResponse searchVPrescriptionPost(@RequestBody SearchPrescriptionRequest request){
        return searchPrescriptionService.execute(request);
    }

    @GetMapping(path = "/search", produces = "application/json")
    public SearchPrescriptionResponse searchPrescriptionResponse(@RequestParam Long prescriptionId,
                                                   @RequestParam Long doctorId,
                                                   @RequestParam Long patientId){
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(prescriptionId, doctorId, patientId);
        return searchPrescriptionService.execute(request);
    }
}
