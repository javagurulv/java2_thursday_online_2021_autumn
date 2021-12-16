package lv.javaguru.java2.oddJobs.web_ui.controllers.rest;

import lv.javaguru.java2.oddJobs.core.requests.add.AddAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.FindAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.requests.get.GetAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.requests.remove.DeleteAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.requests.update.UpdateAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.response.add.AddAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.response.find.FindAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.response.get.GetAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.response.remove.DeleteAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.response.update.UpdateAdvertisementResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddAdvertisementService;
import lv.javaguru.java2.oddJobs.core.services.find.FindAdvertisementService;
import lv.javaguru.java2.oddJobs.core.services.get.GetAdvertisementService;
import lv.javaguru.java2.oddJobs.core.services.remove.DeleteAdvertisementService;
import lv.javaguru.java2.oddJobs.core.services.update.UpdateAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/advertisement")
public class AdvertisementRestController {


    @Autowired
    private GetAdvertisementService getAdvertisementService;
    @Autowired
    private AddAdvertisementService addAdvertisementService;
    @Autowired
    private UpdateAdvertisementService updateAdvertisementService;
    @Autowired
    private DeleteAdvertisementService deleteAdvertisementService;
    @Autowired
    private FindAdvertisementService findAdvertisementService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetAdvertisementResponse getAdvertisement(@PathVariable Long id) {
        GetAdvertisementRequest request = new GetAdvertisementRequest(id);
        return getAdvertisementService.execute(request);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddAdvertisementResponse addAdvertisement(@RequestBody AddAdvertisementRequest request) {
        return addAdvertisementService.execute(request);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public DeleteAdvertisementResponse deleteAdvertisement(@PathVariable Long id) {
        DeleteAdvertisementRequest request = new DeleteAdvertisementRequest(id);
        return deleteAdvertisementService.execute(request);
    }

    @PutMapping(path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public UpdateAdvertisementResponse updateAdvertisement(@RequestBody UpdateAdvertisementRequest request) {
        return updateAdvertisementService.execute(request);
    }

    @PostMapping(path = "/search",
            consumes = "application/json",
            produces = "application/json")
    public FindAdvertisementResponse searchAdvertisementPost(@RequestBody FindAdvertisementRequest request) {
        return findAdvertisementService.execute(request);
    }

    @GetMapping(path = "/search", produces = "application/json")
    public FindAdvertisementResponse searchAdvertisementGet(@RequestParam Long advId,
                                                            @RequestParam String advTitle) {
        FindAdvertisementRequest request = new FindAdvertisementRequest(advId, advTitle);
        return findAdvertisementService.execute(request);
    }
}
