package lv.javaguru.java2.qwe.web_ui.controllers.database;

import lv.javaguru.java2.qwe.core.requests.data_requests.AddStockRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.AddStockResponse;
import lv.javaguru.java2.qwe.core.services.data_services.AddStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddStockController {

    @Autowired
    private AddStockService service;

    @GetMapping(value = "/addStock")
    public String showAddStockPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddStockRequest());
        return "addStock";
    }

    @PostMapping("/addStock")
    public String processAddStockRequest(@ModelAttribute(value = "request") AddStockRequest request, ModelMap modelMap) {
        AddStockResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addStock";
        } else {
            return "redirect:/";
        }
    }

}