package lv.javaguru.java2.jg_entertainment;

import java.util.Objects;

public class Table {

	private Long id;
	private String title;
	private int tableCapacity;

	public Table(String title, int tableCapacity) {
		this.title = title;
		this.tableCapacity = tableCapacity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTableCapacity() {
		return tableCapacity;
	}

	public void setTableCapacity(int tableCapacity) {
		this.tableCapacity = tableCapacity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Table table = (Table) o;
		return Objects.equals(id, table.id) &&
				Objects.equals(title, table.title) &&
				Objects.equals(tableCapacity, table.tableCapacity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, tableCapacity);
	}

	@Override
	public String toString() {
		return "Table{" +
				"id=" + id +
				", title='" + title + '\'' +
				", tableCapacity='" + tableCapacity + '\'' +
				'}';
	}
}
