package lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
public class MenuRepositoryImpl implements MenuRepository {

    private Long nextNr = 1L;
    private List<Menu> menus = new ArrayList<>();

    @Override
    public void save(Menu menu) {
        menu.setNumber(nextNr);
        nextNr++;
        menus.add(menu);
    }

    @Override
    public boolean deleteByNr(Long number) {
        boolean isMenuDeleted = false;
        Optional<Menu> menuToDeleteOpt = menus.stream()
                .filter(menu -> menu.getNumber().equals(number))
                .findFirst();
        if (menuToDeleteOpt.isPresent()) {
            Menu menuToRemove = menuToDeleteOpt.get();
            isMenuDeleted = menus.remove(menuToRemove);
        }
        return isMenuDeleted;
    }

    @Override
    public List<Menu> getAllMenus() {
        return menus;
    }

    // (*new часть ->
    @Override
    public List<Menu> findById(Long number) {
        return menus.stream()
                .filter(menu -> menu.getNumber().equals(number))
                .collect(Collectors.toList());
    }
    // <- досюда часть новая )

    @Override
    public List<Menu> findByTitle(String title) {
        return menus.stream()
                .filter(menu -> menu.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Menu> findByDescription(String description) {
        return menus.stream()
                .filter(menu -> menu.getDescription().equals(description))
                .collect(Collectors.toList());
    }

    @Override
    public List<Menu> findByTitleAndDescription(String title, String description) {
        return menus.stream()
                .filter(menu -> menu.getTitle().equals(title))
                .filter(menu -> menu.getDescription().equals(description))
                .collect(Collectors.toList());
    }
}
