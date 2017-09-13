package database;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Olha Chuchuk on 13.09.2017.
 */
public class DatabaseTest {

    @Test
    public void createDatabaseAndGetNameWithEmptyStructure() {
        DatabaseStructure dbStructure = new DatabaseStructure();
        String dbName = "My new DB";
        Database db = new Database(dbName);

        assertEquals(dbName, db.getName());
    }

}