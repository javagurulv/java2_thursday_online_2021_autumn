package lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

//@Component
public class JdbcTableRepositoryImpl implements TableRepository {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TableRepository tableRepository;

    @Override
    public void save(Table table) {
        jdbcTemplate.update(
                "INSERT INTO tables (table_title, table_capacity, table_price) "
                        + "VALUES (?, ?, ?)",
                table.getTitle(), table.getTableCapacity(), table.getPrice());
        String sql = "SELECT table_id FROM tables WHERE table_title = ? AND table_capacity = ? AND table_price = ?";
        Object[] args = new Object[] {table.getTitle(), table.getTableCapacity(), table.getPrice()};
        Long renewedIdCodeTable = jdbcTemplate.queryForObject(sql, args, Long.class);
        table.setId(renewedIdCodeTable);
    }

    @Override
    public Optional<Table> getById(Long idTable) {
        return Optional.empty();
    }

    @Override
    public List<Table> findTableById(Long idTable) {
        String sql = "SELECT * FROM tables WHERE table_id = ?";
        Object[] args = new Object[] {idTable};
        return jdbcTemplate.query(sql, args, new TableRowMapper());
    }

    @Override
    public List<Table> findByTitleTable(String titleTable) {
        String sql = "SELECT * FROM tables WHERE table_title = ?";
        Object[] args = new Object[] {titleTable};
        return jdbcTemplate.query(sql, args, new TableRowMapper());
    }

    @Override
    public List<Table> findByIdAndTitleTable(Long id, String titleTable) {
        String sql = "SELECT * FROM tables WHERE table_id = ? AND table_title = ?";
        Object[] args = new Object[] {id, titleTable};
        return jdbcTemplate.query(sql, args, new TableRowMapper());
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM tables WHERE table_id = ?";
        Object[] args = new Object[] {id};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Table> getAllTables() {
        String sql = "SELECT * FROM tables";
        return jdbcTemplate.query(sql, new TableRowMapper());
    }
}
