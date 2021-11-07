package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableRowMapper implements RowMapper<Table> {

    @Override
    public Table mapRow(ResultSet rs, int rowNum) throws SQLException {
        Table table = new Table();
        table.setId(rs.getLong("table_id"));
        table.setTitle(rs.getString("table_title"));
        table.setTableCapacity(rs.getInt("table_capacity"));
        table.setPrice(rs.getDouble("table_price"));
        return table;
    }
}
