package lv.javaguru.java2.jg_entertainment.core.database;

import lv.javaguru.java2.jg_entertainment.Table;

import java.util.List;

public interface TableDatabase {

	void save(Table table);

	boolean deleteById(Long id);

	List<Table> getAllTables();

}
