package Model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olha_Chuchuk on 9/14/2017.
 */
public class SertializationTest {

    @Test
    public void serializationWriteAndReadEmptyDB(){
        Database db = new Database("My db");
        Database.serialize(db, "database.out");
        Database readDB = Database.deserialize("database.out");

        assertEquals(db, readDB);
    }

    @Test
    public void serializeDBWithSomeTables(){
        Database database = new Database("Database");

        String tableName1 = "Table 1";
        TableConfig config1 = new TableConfig();
        config1.addColumn("Primary key column", ColumnType.IntegerType, true);
        config1.addColumn("Integer column", ColumnType.IntegerType, false);
        config1.addColumn("Char column", ColumnType.CharType, false);
        config1.addColumn("Real column", ColumnType.RealType, false);
        config1.addColumn("Picture column", ColumnType.PictureType, false);
        database.addTable(tableName1, config1);

        Table table1 = database.getTable(tableName1);
        List<String> row1 = Arrays.asList("1", "123", "a", "0.12345",
                "http://worldartsme.com/images/123-clipart-1.jpg");
        table1.addRow(row1);

        String tableName2 = "Table 2";
        TableConfig config2 = new TableConfig();
        config2.addColumn("Primary key column", ColumnType.IntegerType, true);
        config2.addColumn("Integer column", ColumnType.IntegerType, false);
        config2.addColumn("Char column", ColumnType.CharType, false);
        config2.addColumn("Real column", ColumnType.RealType, false);
        config2.addColumn("Picture column", ColumnType.PictureType, false);
        database.addTable(tableName2, config2);

        Table table2 = database.getTable(tableName2);
        List<String> row2 = Arrays.asList("1", "123", "a", "0.12345",
                "http://worldartsme.com/images/123-clipart-1.jpg");
        table2.addRow(row2);
        Database.serialize(database, "database.db");

        Database readDB = Database.deserialize("database.db");
        assertEquals(database, readDB);
    }
}