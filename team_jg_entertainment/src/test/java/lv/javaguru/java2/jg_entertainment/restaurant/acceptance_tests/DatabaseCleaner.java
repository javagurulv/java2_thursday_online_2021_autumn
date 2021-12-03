package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseCleaner {

    @Autowired private JdbcTemplate jdbcTemplate;

    public void cleaner() {
        for (String tableName : getTableNames()) {
            String sql = "delete from " + tableName;
            jdbcTemplate.execute(sql);
        }
    }

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        tableNames.add("visitors");
        tableNames.add("tables");
        tableNames.add("menu");
        tableNames.add("reservation");
        return tableNames;
    }
}
