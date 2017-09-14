package Model;

/**
 * Created by Olha Chuchuk on 13.09.2017.
 */
public class Column {

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
}
