package domain;

import java.io.Serializable;

/**
 * Created by Olha Chuchuk on 13.09.2017.
 */
public class Column implements Serializable {

    private final String name;
    private final ColumnType type;

    Column(String columnName, ColumnType type) {
        name = columnName;
        this.type = type;
    }

    public ColumnType getColumnType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Column column = (Column) o;

        if (!name.equals(column.name)) return false;
        return type == column.type;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
