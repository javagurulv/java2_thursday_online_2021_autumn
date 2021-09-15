package lv.javaguru.java2.jg_entertainment.menu;

import lv.javaguru.java2.jg_entertainment.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImplDatabaseMenu implements DatabaseMenu {

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
}
