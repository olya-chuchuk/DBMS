package database;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Olha Chuchuk on 13.09.2017.
 */
public final class Database {

    private final String dbName;
    private final Set<Table> tables;

    public Database(String dbName) {
        this.dbName = dbName;
        tables = new HashSet<>();
    }

    public String getName() {
        return dbName;
    }

    public boolean addTable(String tableName, TableConfig config) {
        if(tables.stream().anyMatch(t -> t.getName().equals(tableName))) {
            return false;
        }
        tables.add(new Table(tableName, config));
        return true;
    }

    public Table getTable(String tableName) {
        return tables.stream()
                .filter(t -> t.getName().equals(tableName))
                .findAny()
                .orElseThrow(() -> new NoSuchTableException());
    }

    public void deleteTable(String tableName) {
        Table table = tables.stream()
                .filter(t -> t.getName().equals(tableName))
                .findAny()
                .orElseThrow(NoSuchTableException::new);
        tables.remove(table);
    }
}
