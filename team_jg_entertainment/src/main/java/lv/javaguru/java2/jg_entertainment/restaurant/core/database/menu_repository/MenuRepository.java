package lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;

import java.util.List;


public interface MenuRepository {

    void save(Menu menu);

    boolean deleteByNr(Long number);

    List<Menu> getAllMenus();

    // (*new часть ->
    List<Menu> findById(Long number);

    // <- досюда часть новая )

    List<Menu> findByTitle(String title);

    List<Menu> findByDescription(String description);

    List<Menu> findByTitleAndDescription(String title, String description);
}
