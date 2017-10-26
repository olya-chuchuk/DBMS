package domain;

import exceptions.*;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Olha Chuchuk on 13.09.2017.
 */
public class Table implements Serializable{

    //representation:
    // keyColumn is the number of column which is a primary key in this table
    // must be 0 <= keyColumn < columns.size()
    private final String name;
    private final List<Column> columns;
    private final int columnsCount;
    private final Map<String, List<String>> rows;
    private final int keyColumn;

    Table(String name, TableConfig config) {
        this.name = name;
        columns = config.getColumns();
        columnsCount = columns.size();
        rows = new HashMap<>();
        keyColumn = config.getKeyColumnNumber();
    }

    public String getName() {

        return name;
    }

    public List<Column> getColumns() {

        return Collections.unmodifiableList(columns);
    }

    public void tryMethod() {
        System.out.println("Void method");
    }

    public void addRow(List<String> row) {
        if(row.size() != columns.size()) {
            throw new IllegalRowException();
        }
        String key = row.get(keyColumn);
        if(rows.containsKey(key)) {
            throw new DuplicateKeyException();
        }
        for(int i = 0; i < columnsCount; ++i) {
            switch (columns.get(i).getColumnType()) {
                case IntegerType:
                    try {
                        Integer.parseInt(row.get(i));
                    } catch (NumberFormatException e) {
                        throw new IllegalRowException(e);
                    }
                    break;
                case RealType:
                    try {
                        Double.parseDouble(row.get(i));
                    } catch (NumberFormatException e) {
                        throw new IllegalRowException(e);
                    }
                    break;
                case CharType:
                    if(row.get(i).length() != 1) {
                        throw new IllegalRowException();
                    }
                    break;
                case RealIntervalType:
                    RealInterval.valueOf(row.get(i));
                    break;
            }
        }
        List<String> rowCopy = new LinkedList<>(row);
        rows.put(key, rowCopy);
    }


    public List<List<String>> getRows() {
        return Collections.unmodifiableList(rows.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList()));
    }

    public List<String> getRow(String key) {
        if(!rows.containsKey(key)) {
            throw new NoSuchRowException();
        }
        return Collections.unmodifiableList(rows.get(key));
    }

    public void updateRow(String key, List<String> newRow) {
        String newKey = newRow.get(keyColumn);
        if(!newKey.equals(key) && containsKey(newKey)) {
            throw new DuplicateKeyException();
        }
        deleteRow(key);
        addRow(newRow);
    }

    public void deleteRow(String key) {
        if(!rows.containsKey(key)) {
            throw new NoSuchRowException();
        }
        rows.remove(key);
    }

    public boolean containsKey(String key) {
        return rows.containsKey(key);
    }

    public int getRowsCount() {
        return rows.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;

        if (keyColumn != table.keyColumn) return false;
        if (!name.equals(table.name)) return false;
        if (!columns.equals(table.columns)) return false;
        return rows.equals(table.rows);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + columns.hashCode();
        result = 31 * result + rows.hashCode();
        result = 31 * result + keyColumn;
        return result;
    }

    public Table subtract(Table table2) {
        TableConfig config = getConfig();
        if(!config.equals(table2.getConfig())) {
            throw new IllegalArgumentSubstractException();
        }
        Table table3 = new Table("Residual table", config);
        rows.entrySet().stream()
                    .map(Map.Entry::getValue)
                    .filter(row -> !table2.containsRow(row))
                    .forEach(row -> table3.addRow(row));
        return table3;
    }

    private boolean containsRow(List<String> row) {
        String keyField = row.get(keyColumn);
        if(rows.containsKey(keyField) && rows.get(keyField).equals(row)) {
            return true;
        }
        return false;
    }

    private TableConfig getConfig() {
        TableConfig config = new TableConfig();
        for(Column c: columns) {
            config.addColumn(c.getName(), c.getColumnType());
        }
        config.setPrimaryKey(keyColumn);
        return config;
    }

    public int getKeyColumn() {
        return keyColumn;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(
                "name='" + name + '\'' +
                "\ncolumnsCount=" + columnsCount +
                "\nkeyColumn=" + keyColumn +
                "\ncolumns=" + columns.stream().map(Column::getColumnType).collect(Collectors.toList()) +
                "\nrows=" + '\n');

        rows.values().stream().forEach(row -> res.append(row.toString() + '\n'));
        return res.toString();
    }
}
