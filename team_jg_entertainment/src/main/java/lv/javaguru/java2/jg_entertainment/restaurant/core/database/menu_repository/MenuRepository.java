package lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;

import java.util.List;
import java.util.Optional;


public interface MenuRepository {

    void save(Menu menu);

    Optional<Menu> getById(Long menuId);

    boolean deleteByNr(Long number);

    List<Menu> getAllMenus();

    // (*new часть ->
    List<Menu> findById(Long number);

    // <- досюда часть новая )

    List<Menu> findByTitle(String title);

    List<Menu> findByDescription(String description);

    List<Menu> findByTitleAndDescription(String title, String description);
}
