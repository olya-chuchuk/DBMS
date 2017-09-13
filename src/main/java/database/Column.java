package database;

/**
 * Created by Olha Chuchuk on 13.09.2017.
 */
public class Column {

    private final String name;
    private final ColumnType type;

    Column(String s, ColumnType charType) {
        name = s;
        type = charType;
    }

    public ColumnType getColumnType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
