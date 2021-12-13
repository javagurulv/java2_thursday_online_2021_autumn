package lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;

import java.util.List;
import java.util.Optional;

public interface TableRepository {

    void save(Table table);

    Optional<Table> getById(Long idTable);

    List<Table> findTableById(Long idTable);//*

    List<Table> findByTitleTable(String titleTable);//*

    List<Table> findByIdAndTitleTable(Long id, String titleTable);//*

    boolean deleteById(Long id);

    List<Table> getAllTables();

}
