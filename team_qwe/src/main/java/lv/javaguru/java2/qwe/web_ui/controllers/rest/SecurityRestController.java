package lv.javaguru.java2.qwe.web_ui.controllers.rest;

import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.core.requests.data_requests.AddStockRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.FilterStocksByMultipleParametersRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByTickerOrNameRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.RemoveSecurityRequest;
import lv.javaguru.java2.qwe.core.services.data_services.AddStockService;
import lv.javaguru.java2.qwe.core.services.data_services.FilterStocksByMultipleParametersService;
import lv.javaguru.java2.qwe.core.services.data_services.FindSecurityByTickerOrNameService;
import lv.javaguru.java2.qwe.core.services.data_services.RemoveSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/security")
public class SecurityRestController {

    @Autowired private FindSecurityByTickerOrNameService findSecurityService;
    @Autowired private AddStockService addStockService;
    @Autowired private RemoveSecurityService removeSecurityService;
    @Autowired private FilterStocksByMultipleParametersService filterStocksService;

    @GetMapping(path = "/{ticker}", produces = "application/json")
    public Security getSecurity(@PathVariable String ticker) {
        return findSecurityService.execute(new FindSecurityByTickerOrNameRequest(ticker)).getSecurity();
    }

    @PostMapping(path = "/filter/",
            consumes = "application/json",
            produces = "application/json")
    public List<Security> filterStocks(@RequestBody FilterStocksByMultipleParametersRequest request) {
        System.out.println("RESULT:");
        System.out.println(request.getRequestList());
        return filterStocksService.execute(request).getList();
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public Stock addStock(@RequestBody AddStockRequest request) {
        return addStockService.execute(request).getNewStock();
    }

    @DeleteMapping(path = "/{ticker}", produces = "application/json")
    public boolean removeStock(@PathVariable String ticker) {
        return removeSecurityService.execute(new RemoveSecurityRequest(ticker)).isRemoved();
    }

}