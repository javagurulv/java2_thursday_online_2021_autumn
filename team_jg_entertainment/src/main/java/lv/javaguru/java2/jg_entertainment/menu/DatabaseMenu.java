package lv.javaguru.java2.jg_entertainment.menu;
import lv.javaguru.java2.jg_entertainment.Menu;


import java.util.List;

public interface DatabaseMenu {

        void save(Menu menu);

        boolean deleteByNr(int number);

        List<Menu> getAllMenus();

    }
