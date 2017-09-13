package database;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Olha Chuchuk on 13.09.2017.
 */
public class Table {

    //representation:
    // keyColumn is the number of column which is a primary key in this table
    // must be 0 <= keyColumn < columns.size()
    private final String name;
    private final List<Column> columns;
    private final int keyColumn;

    Table(String name, TableConfig config) {
        this.name = name;
        columns = config.getColumns();
        keyColumn = config.getKeyColumnNumber();
    }

    public String getName() {
        return name;
    }

    public List<Column> getColumns() {
        return Collections.unmodifiableList(columns);
    }
}
