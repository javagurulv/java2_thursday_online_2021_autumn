package lv.javaguru.java2.qwe.web_ui.controllers.rest;

import lv.javaguru.java2.qwe.core.requests.data_requests.AddStockRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByTickerOrNameRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.AddStockResponse;
import lv.javaguru.java2.qwe.core.responses.data_responses.FindSecurityByTickerOrNameResponse;
import lv.javaguru.java2.qwe.core.services.data_services.AddStockService;
import lv.javaguru.java2.qwe.core.services.data_services.FindSecurityByTickerOrNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class SecurityRestController {

    @Autowired private FindSecurityByTickerOrNameService service;
    @Autowired private AddStockService addStockService;

    @GetMapping(path = "/{ticker}", produces = "application/json")
    public FindSecurityByTickerOrNameResponse getSecurity(@PathVariable String ticker) {
        return service.execute(new FindSecurityByTickerOrNameRequest(ticker));
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddStockResponse addStock(@RequestBody AddStockRequest request) {
        return addStockService.execute(request);
    }

}