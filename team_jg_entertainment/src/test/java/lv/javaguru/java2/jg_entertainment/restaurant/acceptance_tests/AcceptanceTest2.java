package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {RestaurantCoreConfiguration.class})
//@Sql({"/Schema.sql"})
//public class AcceptanceTest2 {
//
//    @Autowired private ApplicationContext appContext;
//
//    @BeforeEach
//    public void setup() {
//        getDatabaseCleaner().cleaner();
//    }
//
//    @Test
//    public void searchVisitor() {
//        AddUserRequest request1 = new AddUserRequest("name", "surname1", "252525");
//        getAddVisitorService().execute(request1);
//        AddUserRequest request2 = new AddUserRequest("name", "surname2", "252525");
//        getAddVisitorService().execute(request2);
//        SearchUsersRequest request3 = new SearchUsersRequest("name", null);
//        SearchUsersResponse response = getSearchVisitorService().execute(request3);
//        Assertions.assertEquals(response.getUsers().size(), 2);
//        Assertions.assertEquals(response.getUsers().get(0).getUserName(), "name");
//        Assertions.assertEquals(response.getUsers().get(0).getSurname(), "surname1");
//        Assertions.assertEquals(response.getUsers().get(1).getUserName(), "name");
//        Assertions.assertEquals(response.getUsers().get(1).getSurname(), "surname2");
//    }
//
//    @Test
//    public void searchVisitorsOrderingDescending() {
//        AddUserRequest request1 = new AddUserRequest("name", "surname1", "252525");
//        getAddVisitorService().execute(request1);
//        AddUserRequest request2 = new AddUserRequest("name", "surname2", "252525");
//        getAddVisitorService().execute(request2);
//        Ordering ordering = new Ordering("surname", "DESCENDING");
//        SearchUsersRequest request = new SearchUsersRequest("name", null, ordering);
//        SearchUsersResponse response = getSearchVisitorService().execute(request);
//        Assertions.assertEquals(response.getUsers().size(), 2);
//        Assertions.assertEquals(response.getUsers().get(0).getUserName(), "name");
//        Assertions.assertEquals(response.getUsers().get(0).getSurname(), "surname2");
//        Assertions.assertEquals(response.getUsers().get(1).getUserName(), "name");
//        Assertions.assertEquals(response.getUsers().get(1).getSurname(), "surname1");
//    }
//
//    @Test
//    public void searchVisitorsOrderingAscending() {
//        AddUserRequest addUserRequest = new AddUserRequest("name", "surname1", "252525");
//        getAddVisitorService().execute(addUserRequest);
//        AddUserRequest addUserRequest1 = new AddUserRequest("name", "surname2", "252525");
//        getAddVisitorService().execute(addUserRequest1);
//        Ordering ordering = new Ordering("surname", "ASCENDING");
//        SearchUsersRequest request = new SearchUsersRequest("name", null, ordering);
//        SearchUsersResponse searchUsersResponse = getSearchVisitorService().execute(request);
//        Assertions.assertEquals(searchUsersResponse.getUsers().size(), 2);
//        Assertions.assertEquals(searchUsersResponse.getUsers().get(0).getUserName(), "name");
//        Assertions.assertEquals(searchUsersResponse.getUsers().get(0).getSurname(), "surname1");
//        Assertions.assertEquals(searchUsersResponse.getUsers().get(1).getUserName(), "name");
//        Assertions.assertEquals(searchUsersResponse.getUsers().get(1).getSurname(), "surname2");
//    }
//
//    @Test
//    public void searchVisitorsOrderingPaging() {
//        AddUserRequest addUserRequest = new AddUserRequest("name", "surname1", "252525");
//        getAddVisitorService().execute(addUserRequest);
//        AddUserRequest addUserRequest1 = new AddUserRequest("name", "surname2", "252525");
//        getAddVisitorService().execute(addUserRequest1);
//        Ordering ordering = new Ordering("surname", "ASCENDING");
//        Paging paging = new Paging(1, 1);
//        SearchUsersRequest request = new SearchUsersRequest("name", null, ordering, paging);
//        SearchUsersResponse searchUsersResponse = getSearchVisitorService().execute(request);
//        Assertions.assertEquals(searchUsersResponse.getUsers().size(), 1);
//        Assertions.assertEquals(searchUsersResponse.getUsers().get(0).getUserName(), "name");
//        Assertions.assertEquals(searchUsersResponse.getUsers().get(0).getSurname(), "surname1");
//    }
//
//    private AddAllUsersService getAddVisitorService() {
//        return appContext.getBean(AddAllUsersService.class);
//    }
//
//    private SearchUsersService getSearchVisitorService() {
//        return appContext.getBean(SearchUsersService.class);
//    }
//
//    private DatabaseCleaner getDatabaseCleaner() {
//        return appContext.getBean(DatabaseCleaner.class);
//    }
//
//}
