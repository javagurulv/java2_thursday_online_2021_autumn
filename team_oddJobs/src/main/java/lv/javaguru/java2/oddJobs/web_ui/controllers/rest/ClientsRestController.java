package lv.javaguru.java2.oddJobs.web_ui.controllers.rest;

import lv.javaguru.java2.oddJobs.core.requests.add.AddClientRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.FindClientsRequest;
import lv.javaguru.java2.oddJobs.core.requests.get.GetClientRequest;
import lv.javaguru.java2.oddJobs.core.requests.remove.DeleteClientRequest;
import lv.javaguru.java2.oddJobs.core.requests.update.UpdateClientRequest;
import lv.javaguru.java2.oddJobs.core.response.add.AddClientResponse;
import lv.javaguru.java2.oddJobs.core.response.find.FindClientsResponse;
import lv.javaguru.java2.oddJobs.core.response.get.GetClientResponse;
import lv.javaguru.java2.oddJobs.core.response.remove.DeleteClientResponse;
import lv.javaguru.java2.oddJobs.core.response.update.UpdateClientResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddClientService;
import lv.javaguru.java2.oddJobs.core.services.find.FindClientService;
import lv.javaguru.java2.oddJobs.core.services.get.GetClientService;
import lv.javaguru.java2.oddJobs.core.services.remove.DeleteClientService;
import lv.javaguru.java2.oddJobs.core.services.update.UpdateClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientsRestController {

    @Autowired
    private GetClientService getClientService;
    @Autowired
    private AddClientService addClientService;
    @Autowired
    private UpdateClientService clientService;
    @Autowired
    private DeleteClientService deleteClientService;
    @Autowired
    private FindClientService findClientsService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetClientResponse getClientResponse(@PathVariable Long id) {
        GetClientRequest request = new GetClientRequest(id);
        return getClientService.execute(request);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddClientResponse addClientResponse(@RequestBody AddClientRequest request) {
        return addClientService.execute(request);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public DeleteClientResponse deleteClient(@PathVariable Long id) {
        DeleteClientRequest request = new DeleteClientRequest(id);
        return deleteClientService.execute(request);
    }

    @PutMapping(path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public UpdateClientResponse updateSpecialist(@RequestBody UpdateClientRequest request) {
        return clientService.execute(request);
    }

    @PostMapping(path = "/search",
            consumes = "application/json",
            produces = "application/json")
    public FindClientsResponse searchSpecialistPost(@RequestBody FindClientsRequest request) {
        return findClientsService.execute(request);
    }

    @GetMapping(path = "/search", produces = "application/json")
    public FindClientsResponse searchSpecialistGet(@RequestParam String clientName,
                                                   @RequestParam String clientSurname) {
        FindClientsRequest request = new FindClientsRequest(clientName, clientSurname);
        return findClientsService.execute(request);
    }
}
