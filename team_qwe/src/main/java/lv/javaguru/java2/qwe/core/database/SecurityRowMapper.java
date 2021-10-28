package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.Stock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SecurityRowMapper implements RowMapper<Security> {

    @Override
    public Security mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Stock(
                rs.getString("ticker"),
                rs.getString("name"),
                rs.getString("industry"),
                rs.getString("currency"),
                rs.getDouble("market_price"),
                rs.getDouble("dividend_yield"),
                rs.getDouble("risk_weight")
        );
    }

}