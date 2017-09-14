package Model;

import Exceptions.DuplicateKeyException;
import Exceptions.IllegalRowException;
import Exceptions.NoSuchRowException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Olha Chuchuk on 13.09.2017.
 */
public class Table {

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
        deleteRow(key);
        addRow(newRow);
    }

    public void deleteRow(String key) {
        if(!rows.containsKey(key)) {
            throw new NoSuchRowException();
        }
        rows.remove(key);
    }

    public int getRowsCount() {
        return rows.size();
    }
}
