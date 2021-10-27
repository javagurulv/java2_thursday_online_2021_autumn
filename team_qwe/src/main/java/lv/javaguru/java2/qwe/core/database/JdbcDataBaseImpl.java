package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.core.domain.Bond;
import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.core.requests.data_requests.FilterStocksByMultipleParametersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcDataBaseImpl implements Database {

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<Security> getSecurityList() {
        return null;
    }

    @Override
    public void addStock(Stock stock) {
        jdbcTemplate.update("INSERT INTO stocks (ticker, name, industry, currency, market_price, dividend_yield, risk_weight) VALUES\n" +
                        "(?, ?, ?, ?, ?, ?, ?)",
                stock.getTicker(), stock.getName(), stock.getIndustry(), stock.getCurrency(),
                stock.getMarketPrice(), stock.getDividends(), stock.getRiskWeight());
    }

    @Override
    public void addBond(Bond bond) {
        jdbcTemplate.update("INSERT INTO bonds (ticker, name, industry, currency, market_price, coupon, rating, nominal, maturity) VALUES\n" +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                bond.getTicker(), bond.getName(), bond.getIndustry(), bond.getCurrency(), bond.getMarketPrice(),
                bond.getCoupon(), bond.getRating(), bond.getNominal(), bond.getMaturity());
    }

    @Override
    public boolean removeSecurity(String ticker) {
        return (ticker.contains(" ")) ? jdbcTemplate.update("DELETE FROM stocks\n" +
                "  WHERE ticker = ?", ticker) == 1
                : jdbcTemplate.update("DELETE FROM bonds\n" +
                "  WHERE ticker = ?", ticker) == 1;
    }

    @Override
    public List<Security> getAllSecurityList() {
        String sql = "SELECT * FROM stocks";
        return jdbcTemplate.query(sql, new SecurityRowMapper());
    }

    @Override
    public Optional<Security> findSecurityByTickerOrName(String name) {
        String sql = "SELECT * FROM stocks\n" +
                "  WHERE ticker = ? OR name = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new SecurityRowMapper(), name, name));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Security> filterStocksByMultipleParameters(String sql) {
        return jdbcTemplate.query(sql, new SecurityRowMapper());
    }

}