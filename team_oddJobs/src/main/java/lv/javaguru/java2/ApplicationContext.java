package lv.javaguru.java2;

import lv.javaguru.java2.console_ui.Add.AddClientUIAction;
import lv.javaguru.java2.console_ui.Add.AddSpecialistUIAction;
import lv.javaguru.java2.console_ui.Exit.ExitMenuUIAction;
import lv.javaguru.java2.console_ui.Find.FindClientsUIAction;
import lv.javaguru.java2.console_ui.Find.FindSpecialistUIAction;
import lv.javaguru.java2.console_ui.Get.GetAllClientsUIAction;
import lv.javaguru.java2.console_ui.Get.GetAllSpecialistUIAction;
import lv.javaguru.java2.console_ui.Remove.RemoveClientUIAction;
import lv.javaguru.java2.console_ui.Remove.RemoveSpecialistUIAction;
import lv.javaguru.java2.core.validations.*;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.services.Add.AddClientService;
import lv.javaguru.java2.services.Add.AddSpecialistService;
import lv.javaguru.java2.services.Exit.ExitMenuService;
import lv.javaguru.java2.services.Find.FindClientsService;
import lv.javaguru.java2.services.Find.FindSpecialistService;
import lv.javaguru.java2.services.Get.GetAllClientsService;
import lv.javaguru.java2.services.Get.GetAllSpecialistsService;
import lv.javaguru.java2.services.Remove.RemoveClientService;
import lv.javaguru.java2.services.Remove.RemoveSpecialistService;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {
    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(Database.class, new InMemoryDatabaseImpl());
        beans.put(AddClientValidator.class, new AddClientValidator());
        beans.put(AddSpecialistValidator.class, new AddSpecialistValidator());
        beans.put(RemoveClientValidator.class, new RemoveClientValidator());
        beans.put(RemoveSpecialistValidator.class, new RemoveSpecialistValidator());
        beans.put(FindClientsRequestValidator.class, new FindClientsRequestValidator());
        beans.put(FindSpecialistValidator.class, new FindSpecialistValidator());


        beans.put(AddClientService.class, new AddClientService(getBean(Database.class), getBean(AddClientValidator.class)));
        beans.put(AddSpecialistService.class, new AddSpecialistService(getBean(Database.class), getBean(AddSpecialistValidator.class)));
        beans.put(RemoveClientService.class, new RemoveClientService(getBean(Database.class), getBean(RemoveClientValidator.class)));
        beans.put(RemoveSpecialistService.class, new RemoveSpecialistService(getBean(Database.class), getBean(RemoveSpecialistValidator.class)));
        beans.put(FindClientsService.class, new FindClientsService(getBean(Database.class), getBean(FindClientsRequestValidator.class)));
        beans.put(FindSpecialistService.class, new FindSpecialistService(getBean(Database.class), getBean(FindSpecialistValidator.class)));
        beans.put(GetAllClientsService.class, new GetAllClientsService(getBean(Database.class)));
        beans.put(GetAllSpecialistsService.class, new GetAllSpecialistsService(getBean(Database.class)));

        beans.put(AddClientUIAction.class, new AddClientUIAction(getBean(AddClientService.class)));
        beans.put(AddSpecialistUIAction.class, new AddSpecialistUIAction(getBean(AddSpecialistService.class)));
        beans.put(RemoveClientUIAction.class, new RemoveClientUIAction(getBean(RemoveClientService.class)));
        beans.put(RemoveSpecialistUIAction.class, new RemoveSpecialistUIAction(getBean(RemoveSpecialistService.class)));
        beans.put(FindClientsUIAction.class, new FindClientsUIAction(getBean(FindClientsService.class)));
        beans.put(FindSpecialistUIAction.class, new FindSpecialistUIAction(getBean(FindSpecialistService.class)));
        beans.put(GetAllClientsUIAction.class, new GetAllClientsUIAction(getBean(GetAllClientsService.class)));
        beans.put(GetAllSpecialistUIAction.class, new GetAllSpecialistUIAction(getBean(GetAllSpecialistsService.class)));
        beans.put(ExitMenuUIAction.class, new ExitMenuUIAction(getBean(ExitMenuService.class)));


    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}
