package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcMenuDatabaseImpl implements DatabaseMenu {

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Menu menu) {
        jdbcTemplate.update(
                "INSERT INTO menu (menu_title, menu_description, menu_price) "
                        + "VALUES (?, ?, ?)",
                menu.getTitle(), menu.getDescription(), menu.getPrice());
        String sql = "SELECT menu_id FROM menu WHERE menu_title = ? AND menu_description = ? AND menu_price = ?";
        Object[] args = new Object[]{menu.getTitle(), menu.getDescription(), menu.getPrice()};
        Long renewedIdCodeMenu = jdbcTemplate.queryForObject(sql, args, Long.class);
        menu.setNumber(renewedIdCodeMenu);
    }

    @Override
    public boolean deleteByNr(Long number) {
        String sql = "DELETE FROM menu WHERE menu_id = ?";
        Object[] args = new Object[]{number};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Menu> getAllMenus() {
        String sql = "SELECT * FROM menu";
        return jdbcTemplate.query(sql, new MenuRowMapper());
    }

    // (*new часть ->
    @Override
    public List<Menu> findById(Long idNumber) {
        String sql = "SELECT * FROM menu WHERE menu_id = ?";
        Object[] args = new Object[]{idNumber};
        return jdbcTemplate.query(sql, args, new MenuRowMapper());
    }
    // <- досюда часть новая )

    @Override
    public List<Menu> findByTitle(String title) {
        String sql = "SELECT * FROM menu WHERE menu_title = ?";
        Object[] args = new Object[]{title};
        return jdbcTemplate.query(sql, args, new MenuRowMapper());
    }

    @Override
    public List<Menu> findByDescription(String description) {
        String sql = "SELECT * FROM menu WHERE menu_description = ?";
        Object[] args = new Object[]{description};
        return jdbcTemplate.query(sql, args, new MenuRowMapper());
    }

    @Override
    public List<Menu> findByTitleAndDescription(String title, String description) {
        String sql = "SELECT * FROM menu WHERE menu_title = ? AND menu_description = ?";
        Object[] args = new Object[]{title, description};
        return jdbcTemplate.query(sql, args, new MenuRowMapper());
    }
}