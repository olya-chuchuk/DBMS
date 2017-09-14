package Controller;

import Exceptions.NoSuchDatabaseException;
import Model.Database;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Olha Chuchuk on 13.09.2017.
 */
public class Controller {

    private Set<Database> databaseSet;

    public Controller() {
        databaseSet = new HashSet<Database>();
    }

    public boolean createDatabase(String dbName) {
        if(databaseSet.stream().anyMatch(db -> db.getName().equals(dbName))) {
            return false;
        }
        databaseSet.add(new Database(dbName));
        return true;
    }

    public Database getDatabase(String dbName) {
        return databaseSet.stream()
                .filter(db -> db.getName().equals(dbName))
                .findAny()
                .orElseThrow(() -> new NoSuchDatabaseException());
    }
}
