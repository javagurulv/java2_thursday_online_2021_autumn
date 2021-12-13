package lv.javaguru.java2.jg_entertainment.restaurant.web_ui.controllers.rest;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired private GetUserService getUserService;
    @Autowired private AddAllUsersService addUsersService;
    @Autowired private UpdateUserService updateUserService;
    @Autowired private RemoveUserIdService deleteUsersService;
    @Autowired private ShowListUsersService showListUsersService;
    @Autowired private SearchUsersService searchUsersService;


    @GetMapping(path = "/{userId}",
            produces = "application/json")
    public GetUserResponse getUser(@PathVariable Long userId) {
        GetUserRequest request = new GetUserRequest(userId);
        return getUserService.execute(request);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddUsersResponse addUser(@RequestBody AddUserRequest request) {
        return addUsersService.execute(request);
    }

    @PutMapping(path = "/{userId}",
            consumes = "application/json",
            produces = "application/json")
    public UpdateUserResponse updateUser(@RequestBody UpdateUserRequest request){
        return updateUserService.execute(request);
    }

    @DeleteMapping(path = "/{id}",
            produces = "application/json")//delete Id
    public RemoveUserResponse deleteUser(@PathVariable Long id) {
        RemoveUserRequest request = new RemoveUserRequest(id);
        return deleteUsersService.execute(request);
    }

    @GetMapping(path = "/",
            produces = "application/json")
    public ShowAllUsersResponse showAllUsers(){
        ShowAllUsersRequest request = new ShowAllUsersRequest();
        return showListUsersService.execute(request);
    }

    @PostMapping(path = "/search",
            consumes = "application/json",
            produces = "application/json")
    public SearchUsersResponse searchUsersPost(@RequestBody SearchUsersRequest request) {
        return searchUsersService.execute(request);
    }

    @GetMapping(path = "/search",
            produces = "application/json")
    public SearchUsersResponse searchUsersGet(@RequestParam String userName,
                                              @RequestParam String usersSurname) {
        SearchUsersRequest request = new SearchUsersRequest(userName, usersSurname);
        return searchUsersService.execute(request);
    }
}
