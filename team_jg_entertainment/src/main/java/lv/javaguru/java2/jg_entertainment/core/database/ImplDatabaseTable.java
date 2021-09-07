package lv.javaguru.java2.jg_entertainment.core.database;

import lv.javaguru.java2.jg_entertainment.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImplDatabaseTable implements TableDatabase {

	private Long nextId = 1L;
	private List<Table> tables = new ArrayList<>();

	@Override
	public void save(Table table) {
		table.setId(nextId);
		nextId++;
		tables.add(table);
	}

	@Override
	public boolean deleteById(Long id) {
		boolean isTableDeleted = false;
		Optional<Table> tableToDeleteOpt = tables.stream()
				.filter(table -> table.getId().equals(id))
				.findFirst();
		if (tableToDeleteOpt.isPresent()) {
			Table tableToRemove = tableToDeleteOpt.get();
			isTableDeleted = tables.remove(tableToRemove);
		}
		return isTableDeleted;
	}

	@Override
	public List<Table> getAllTables() {
		return tables;
	}
}
