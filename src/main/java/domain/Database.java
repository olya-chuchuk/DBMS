package domain;

import Exceptions.DuplicateTableNameException;
import Exceptions.NoSuchTableException;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Olha Chuchuk on 13.09.2017.
 */
public final class Database implements Serializable{

    private final String dbName;
    private final Map<String, Table> tables;

    public Database(String dbName) {
        this.dbName = dbName;
        tables = new HashMap<>();
    }

    public String getName() {
        return dbName;
    }

    public void addTable(String tableName, TableConfig config) {
        if(tables.containsKey(tableName)) {
            throw new DuplicateTableNameException();
        }
        tables.put(tableName, new Table(tableName, config));
    }

    public Table getTable(String tableName) {
        if(tables.containsKey(tableName)){
            return tables.get(tableName);
        } else {
            throw new NoSuchTableException();
        }
    }

    public void deleteTable(String tableName) {
        if(tables.containsKey(tableName)){
            tables.remove(tableName);
        } else {
            throw new NoSuchTableException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Database database = (Database) o;

        if (!dbName.equals(database.dbName)) return false;
        return tables.equals(database.tables);

    }

    @Override
    public int hashCode() {
        int result = dbName.hashCode();
        result = 31 * result + tables.hashCode();
        return result;
    }

    public static void serialize(Database db, String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(db);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Database deserialize(String fileName) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            return (Database) in.readObject();
        }  catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Table> getTableList() {
        return new LinkedList<>(tables.values());
    }
}
