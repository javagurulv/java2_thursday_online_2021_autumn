package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.Menu;

import java.util.List;

public interface DatabaseMenu {

    void save(Menu menu);

    boolean deleteByNr(Long number);

    List<Menu> getAllMenus();

}
