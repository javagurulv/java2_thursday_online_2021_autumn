package lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

//@Component
public class JdbcMenuRepositoryImpl implements MenuRepository {

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Menu menu) {
        jdbcTemplate.update(
                "INSERT INTO menus (menu_title, menu_description, menu_price) "
                        + "VALUES (?, ?, ?)",
                menu.getTitle(), menu.getDescription(), menu.getPrice());
        String sql = "SELECT menu_id FROM menu WHERE menu_title = ? AND menu_description = ? AND menu_price = ?";
        Object[] args = new Object[]{menu.getTitle(), menu.getDescription(), menu.getPrice()};
        Long renewedIdCodeMenu = jdbcTemplate.queryForObject(sql, args, Long.class);
        menu.setNumber(renewedIdCodeMenu);
    }

    @Override
    public Optional<Menu> getById(Long menuId) {
        return Optional.empty();
    }

    @Override
    public boolean deleteByNr(Long number) {
        String sql = "DELETE FROM menus WHERE menu_id = ?";
        Object[] args = new Object[]{number};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Menu> getAllMenus() {
        String sql = "SELECT * FROM menus";
        return jdbcTemplate.query(sql, new MenuRowMapper());
    }

    // (*new часть ->
    @Override
    public List<Menu> findById(Long number) {
        String sql = "SELECT * FROM menus WHERE menu_id = ?";
        Object[] args = new Object[]{number};
        return jdbcTemplate.query(sql, args, new MenuRowMapper());
    }
    // <- досюда часть новая )

    @Override
    public List<Menu> findByTitle(String title) {
        String sql = "SELECT * FROM menus WHERE menu_title = ?";
        Object[] args = new Object[]{title};
        return jdbcTemplate.query(sql, args, new MenuRowMapper());
    }

    @Override
    public List<Menu> findByDescription(String description) {
        String sql = "SELECT * FROM menus WHERE menu_description = ?";
        Object[] args = new Object[]{description};
        return jdbcTemplate.query(sql, args, new MenuRowMapper());
    }

    @Override
    public List<Menu> findByTitleAndDescription(String title, String description) {
        String sql = "SELECT * FROM menus WHERE menu_title = ? AND menu_description = ?";
        Object[] args = new Object[]{title, description};
        return jdbcTemplate.query(sql, args, new MenuRowMapper());
    }
}