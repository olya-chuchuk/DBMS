package domain;

import Exceptions.IllegalPrimaryKeyException;
import Exceptions.NoPrimaryKeyException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Olha Chuchuk on 13.09.2017.
 */
public class TableConfig {

    private List<Column> columns;
    private int keyColumn;

    public TableConfig() {
        columns = new LinkedList<>();
        keyColumn = -1;
    }

    public void addColumn(String s, ColumnType charType) {
        columns.add(new Column(s, charType));
    }

    public void addColumn(String s, ColumnType charType, boolean isPrimary) {
        if(isPrimary && charType.equals(ColumnType.PictureType)) {
            throw new IllegalPrimaryKeyException();
        }
        columns.add(new Column(s, charType));
        if(isPrimary) {
            keyColumn = columns.size() - 1;
        }
    }

    public List<Column> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    public int getKeyColumnNumber() {
        if(keyColumn == -1) {
            throw new NoPrimaryKeyException();
        }
        return keyColumn;
    }

    public void setPrimaryKey(int primaryKey) {
        keyColumn = primaryKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableConfig config = (TableConfig) o;

        if (keyColumn != config.keyColumn) return false;
        return columns.equals(config.columns);

    }

    @Override
    public int hashCode() {
        int result = columns.hashCode();
        result = 31 * result + keyColumn;
        return result;
    }
}
