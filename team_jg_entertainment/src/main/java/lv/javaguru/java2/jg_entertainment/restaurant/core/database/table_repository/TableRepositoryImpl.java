package lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
public class TableRepositoryImpl implements TableRepository {

    private Long nextId = 1L;
    private List<Table> tables = new ArrayList<>();

    @Override
    public void save(Table table) {
        table.setId(nextId);
        nextId++;
        tables.add(table);
    }

    @Override
    public Optional<Table> getById(Long idTable) {
        return Optional.empty();
    }

    @Override
    public List<Table> findTableById(Long idTable) {///*
        return tables.stream()
                .filter(table -> table.getId().equals(idTable))
                .collect(Collectors.toList());
    }

    @Override
    public List<Table> findByTitleTable(String titleTable) {///*
        return tables.stream()
                .filter(table -> table.getTitle().equals(titleTable))
                .collect(Collectors.toList());
    }

    @Override
    public List<Table> findByIdAndTitleTable(Long id, String titleTable) {///*
        return tables.stream()
                .filter(table -> table.getId().equals(id))
                .filter(table -> table.getTitle().equals(titleTable))
                .collect(Collectors.toList());
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
