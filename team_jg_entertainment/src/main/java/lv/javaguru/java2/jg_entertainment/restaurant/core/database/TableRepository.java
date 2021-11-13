package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;

import java.util.List;

public interface TableRepository {

    void save(Table table);

    List<Table> findTableById(Long idTable);//*

    List<Table> findByTitleTable(String titleTable);//*

    List<Table> findByIdAndTitleTable(Long id, String titleTable);//*

    boolean deleteById(Long id);

    List<Table> getAllTables();

}
