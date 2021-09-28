package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;

import java.util.List;

public interface DatabaseMenu {

    void save(Menu menu);

    boolean deleteByNr(Long number);

    List<Menu> getAllMenus();

    List<Menu> findByTitle(String title);

    List<Menu> findByDescription(String description);

    List<Menu> findByTitleAndDescription(String title, String description);

}
