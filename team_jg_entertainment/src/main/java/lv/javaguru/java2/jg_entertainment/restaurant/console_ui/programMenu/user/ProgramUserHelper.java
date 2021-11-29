package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu.user;

//
//@Component
//public class ProgramUserHelper {
//    private Map<Integer, Menu> menuNumberToTypeOfMenuMap;
//    private static final EnteredNumChecker enteredNumChecker = new EnteredNumChecker();
//
//    @Autowired
//    public ProgramUserHelper(List<Menu> menus) {
//        menuNumberToTypeOfMenuMap = new HashMap<>();
//        menuNumberToTypeOfMenuMap.put(1, findMenu(menus, MenuLevelUser.class));
//        menuNumberToTypeOfMenuMap.put(2, findMenu(menus, ReservationMenu.class));
//        menuNumberToTypeOfMenuMap.put(3, findMenu(menus, ExitMenu.class));
//    }
//
//    private Menu findMenu(List<Menu> menus, Class menusClass) {
//        return menus.stream()
//                .filter(menu -> menu.getClass().equals(menusClass))
//                .findFirst()
//                .get();
//    }
//
//    public void printMainMenu() {
//        System.out.println();
//        System.out.println("Hello ! MENU: ");
//        System.out.println("1--> choose action with USERS: ");
//        System.out.println("2--> choose action with RESERVATION: ");
//        System.out.println("3--> EXIT! ");
//        System.out.println();
//    }
//
//    public int getMenuNumberFromUser() {
//        System.out.println("Enter menu item number to execute: ");
//        return enteredNumChecker.execute(1, 3);
//    }
//
//    public void executeSelectedMenuItem(int selectedMenu, ApplicationContext applicationContext) {
//        menuNumberToTypeOfMenuMap.get(selectedMenu).execute(applicationContext);
//    }
//}
