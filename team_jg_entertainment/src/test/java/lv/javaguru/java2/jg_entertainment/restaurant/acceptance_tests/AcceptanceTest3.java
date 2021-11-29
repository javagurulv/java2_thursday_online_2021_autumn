package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {RestaurantCoreConfiguration.class})
//@Sql({"/Schema.sql"})
//public class AcceptanceTest3 {
//
//    @Autowired private ApplicationContext applicationContext;
//
//    @BeforeEach
//    public void setUp() {
//        databaseCleaner().cleaner();
//    }
//
//    @Test
//    public void shouldDeleteUser() {
//        AddUserRequest addUser1 = new AddUserRequest("name", "surname", "252525");
//        AddUsersResponse addUserResponse1 = addAllUsersService().execute(addUser1);
//        addUserResponse1.getNewUser().getUserId();
//        AddUserRequest addUser2 = new AddUserRequest("name", "surname2", "252525");
//        AddUsersResponse response = addAllUsersService().execute(addUser2);
//        Long idUser2 = response.getNewUser().getUserId();
//        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(idUser2, "name");
//        DeleteUsersResponse deleteVisitorsResponse = deleteUsersService().execute(deleteUserRequest);
//        assertTrue(deleteVisitorsResponse.ifUserIdDeleted());
//        ShowAllUsersResponse showUserResponse = showListUsersService().execute(new ShowAllUsersRequest());
//        assertEquals(showUserResponse.getNewUser().size(), 1);
//        assertEquals(showUserResponse.getNewUser().get(0).getUserName(), "name");
//        assertEquals(showUserResponse.getNewUser().get(0).getSurname(), "surname");
//        assertEquals(showUserResponse.getNewUser().get(0).getTelephoneNumber(), "252525");
//    }
//
//    private AddAllUsersService addAllUsersService() {
//        return applicationContext.getBean(AddAllUsersService.class);
//    }
//
//    private DeleteUsersService deleteUsersService() {
//        return applicationContext.getBean(DeleteUsersService.class);
//    }
//
//    private ShowListUsersService showListUsersService() {
//        return applicationContext.getBean(ShowListUsersService.class);
//    }
//
//    private DatabaseCleaner databaseCleaner() {
//        return applicationContext.getBean(DatabaseCleaner.class);
//    }
//}
