import java.util.Objects;

public class Table {
    private String table;
    private int tablePlaceQty;

    public Table(String table) {
        this.table = table;
    }

    public Table(String table,int tablePlaceQty) {
        this.table = table;
        this.tablePlaceQty = tablePlaceQty;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public int getTablePlaceQty() {
        return tablePlaceQty;
    }

    public void setTablePlaceQty(int tablePlaceQty) {
        this.tablePlaceQty = tablePlaceQty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table1 = (Table) o;
        return tablePlaceQty == table1.tablePlaceQty && table.equals(table1.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, tablePlaceQty);
    }

    @Override
    public String toString() {
        return "Table{" +
                "table='" + table + '\'' +
                ", tablePlaceQty=" + tablePlaceQty +
                '}';


    }
}
