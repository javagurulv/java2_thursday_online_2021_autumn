package lv.javaguru.java2.qwe.web_ui.controllers.rest;

import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByTickerOrNameRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.FindSecurityByTickerOrNameResponse;
import lv.javaguru.java2.qwe.core.services.data_services.FindSecurityByTickerOrNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityRestController {

    @Autowired private FindSecurityByTickerOrNameService service;

    @GetMapping(path = "/{ticker}", produces = "application/json")
    public FindSecurityByTickerOrNameResponse getSecurity(@PathVariable String ticker) {
        return service.execute(new FindSecurityByTickerOrNameRequest(ticker));
    }

}