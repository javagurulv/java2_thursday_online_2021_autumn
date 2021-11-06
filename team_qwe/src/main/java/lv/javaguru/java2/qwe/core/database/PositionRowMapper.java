package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.core.domain.Position;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionRowMapper implements RowMapper<Position> {

    private final Database database;

    public PositionRowMapper(Database database) {
        this.database = database;
    }

    @Override
    public Position mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Position(
                database.findSecurityByTickerOrName(rs.getString("security_ticker")).get(),
                rs.getInt("amount"),
                rs.getDouble("purchase_price")
        );

    }

}