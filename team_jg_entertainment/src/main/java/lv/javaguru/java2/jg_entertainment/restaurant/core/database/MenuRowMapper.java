package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuRowMapper implements RowMapper<Menu> {

    @Override
    public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
        Menu menu = new Menu();
        menu.setNumber(rs.getLong("menu_id"));
        menu.setTitle(rs.getString("menu_title"));
        menu.setDescription(rs.getString("menu_description"));
        menu.setPrice(rs.getDouble("menu_price"));
        return menu;
    }
}
