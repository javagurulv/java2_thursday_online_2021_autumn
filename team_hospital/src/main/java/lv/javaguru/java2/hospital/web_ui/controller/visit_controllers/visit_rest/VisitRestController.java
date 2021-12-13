package lv.javaguru.java2.hospital.web_ui.controller.visit_controllers.visit_rest;

import lv.javaguru.java2.hospital.visit.core.requests.*;
import lv.javaguru.java2.hospital.visit.core.responses.*;
import lv.javaguru.java2.hospital.visit.core.services.AddVisitService;
import lv.javaguru.java2.hospital.visit.core.services.DeleteVisitService;
import lv.javaguru.java2.hospital.visit.core.services.EditVisitService;
import lv.javaguru.java2.hospital.visit.core.services.ShowAllVisitService;
import lv.javaguru.java2.hospital.visit.core.services.search_visit_service.SearchVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visit")
public class VisitRestController {

    @Autowired private AddVisitService addVisitService;
    @Autowired private DeleteVisitService deleteVisitService;
    @Autowired private EditVisitService editVisitService;
    @Autowired private ShowAllVisitService showAllVisitService;
    @Autowired private SearchVisitService searchVisitService;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddVisitResponse addVisit(@RequestBody AddVisitRequest request){
        return addVisitService.execute(request);
    }

    @DeleteMapping(path = "{id}", produces = "application/json")
    public DeleteVisitResponse deleteVisit(@PathVariable String id){
        return deleteVisitService.execute(new DeleteVisitRequest(id));
    }

    @PutMapping(path = "{id}",
            consumes = "application/json",
            produces = "application/json")
    public EditVisitResponse editVisit(@RequestBody EditVisitRequest request){
        return editVisitService.execute(request);
    }

    @GetMapping(path = "/", produces = "application/json")
    public ShowAllVisitResponse showAllVisit(){
        return showAllVisitService.execute(new ShowAllVisitRequest());
    }

    @PostMapping(path = "/search",
            consumes = "application/json",
            produces = "application/json")
    public SearchVisitResponse searchVisitPost(@RequestBody SearchVisitRequest request){
        return searchVisitService.execute(request);
    }

    @GetMapping(path = "/search", produces = "application/json")
    public SearchVisitResponse searchVisitResponse(@RequestParam String visitID,
                                                   @RequestParam String doctorID,
                                                   @RequestParam String patientID,
                                                   @RequestParam String visitDate){
        return searchVisitService.execute(new SearchVisitRequest(visitID, doctorID, patientID, visitDate));
    }
}
