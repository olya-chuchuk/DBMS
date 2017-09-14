package Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olha Chuchuk on 13.09.2017.
 */
public class DatabaseTest {

    @Test
    public void createDatabaseAndGetNameWithEmptyStructure() {
        String dbName = "My new DB";
        Database db = new Database(dbName);

        assertEquals(dbName, db.getName());
    }

}