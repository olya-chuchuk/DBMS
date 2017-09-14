package Controller;

import Exceptions.IllegalPrimaryKeyException;
import Exceptions.NoPrimaryKeyExcpetion;
import Exceptions.NoSuchTableException;
import Model.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by Olha Chuchuk on 13.09.2017.
 */
public class ControllerTest {

    @Test
    public void createEmptyDatabase() {
        String dbName = "My DB";
        Controller controller = new Controller();
        boolean isCreated = controller.createDatabase(dbName);

        Database db = controller.getDatabase(dbName);
        assertTrue(isCreated);
        assertEquals(dbName, db.getName());
    }


    @Test
    public void createDatabaseWithSameName() {
        String dbName = "My DB";
        Controller controller = new Controller();
        controller.createDatabase(dbName);
        boolean isCreated = controller.createDatabase(dbName);

        Database db = controller.getDatabase(dbName);
        assertFalse(isCreated);
        assertEquals(dbName, db.getName());
    }

    @Test
    public void addTableWithSimpleConfigToDatabase() {
        String dbName = "My DB";
        String tableName = "My table";
        Controller controller = new Controller();
        controller.createDatabase(dbName);
        TableConfig tableConfig = new TableConfig();
        tableConfig.addColumn("Column", ColumnType.IntegerType, true);
        boolean isAdded = controller.getDatabase(dbName).addTable(tableName, tableConfig);

        Table table = controller.getDatabase(dbName).getTable(tableName);

        assertTrue(isAdded);
        assertEquals(tableName, table.getName());
    }

    @Test
    public void addTwoTablesWithSameNameIntoOneDB() {
        String dbName = "My DB";
        String tableName = "My table";
        Controller controller = new Controller();
        controller.createDatabase(dbName);
        TableConfig tableConfig = new TableConfig();
        tableConfig.addColumn("Column", ColumnType.IntegerType, true);
        controller.getDatabase(dbName).addTable(tableName, tableConfig);
        boolean isAdded = controller.getDatabase(dbName).addTable(tableName, tableConfig);

        Table table = controller.getDatabase(dbName).getTable(tableName);

        assertFalse(isAdded);
        assertEquals(tableName, table.getName());
    }

    @Test
    public void addTwoTablesWithSameNameIntoDifferentDBs() {
        String dbName1 = "My DB1";
        String dbName2 = "My DB2";
        String tableName = "My table";
        Controller controller = new Controller();
        controller.createDatabase(dbName1);
        controller.createDatabase(dbName2);
        TableConfig tableConfig = new TableConfig();
        tableConfig.addColumn("Column", ColumnType.IntegerType, true);
        controller.getDatabase(dbName1).addTable(tableName, tableConfig);
        controller.getDatabase(dbName2).addTable(tableName, tableConfig);

        Table table1 = controller.getDatabase(dbName1).getTable(tableName);
        Table table2 = controller.getDatabase(dbName2).getTable(tableName);

        assertEquals(tableName, table1.getName());
        assertEquals(tableName, table2.getName());
    }


    @Test(expected = NoSuchTableException.class)
    public void deleteExistingTable() {
        String dbName = "My DB";
        Controller controller = new Controller();
        controller.createDatabase(dbName);
        Database db = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig tableConfig = new TableConfig();
        tableConfig.addColumn("Column", ColumnType.IntegerType, true);
        db.addTable(tableName, tableConfig);
        db.deleteTable(tableName);

        Table table = db.getTable(tableName);

        assertEquals(tableName, table.getName());
    }


    @Test(expected = NoSuchTableException.class)
    public void deleteNonExistingTable() {
        String dbName = "My DB";
        Controller controller = new Controller();
        controller.createDatabase(dbName);
        Database db = controller.getDatabase(dbName);

        String tableName = "My table";
        db.deleteTable(tableName);

        db.getTable(tableName);
    }

    @Test
    public void createTableWithColumnsDifferentNames() {
        String dbName = "My DB";
        Controller controller = new Controller();
        controller.createDatabase(dbName);
        Database db = controller.getDatabase(dbName);
        String tableName = "My table";
        TableConfig config = new TableConfig();
        List<String> colNames = Arrays.asList("First column", "Second column", "Third column");
        for(String name: colNames) {
            config.addColumn(name, ColumnType.IntegerType);
        }
        config.setPrimaryKey(0);
        db.addTable(tableName, config);

        Table table = db.getTable(tableName);
        List<Column> columns = table.getColumns();
        List<String> actual = columns.stream()
                .map(Column::getName)
                .collect(Collectors.toList());

        assertEquals(colNames, actual);
    }

    @Test
    public void createTableWithReadColumn() {
        String dbName = "My DB";
        Controller controller = new Controller();
        controller.createDatabase(dbName);
        Database db = controller.getDatabase(dbName);
        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Read column", ColumnType.RealType, true);
        db.addTable(tableName, config);

        Table table = db.getTable(tableName);
        Column column = table.getColumns().get(0);

        assertEquals(ColumnType.RealType, column.getColumnType());
    }




    @Test
    public void createTableWithCharColumn() {
        String dbName = "My DB";
        Controller controller = new Controller();
        controller.createDatabase(dbName);
        Database db = controller.getDatabase(dbName);
        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Char column", ColumnType.CharType, true);
        db.addTable(tableName, config);

        Table table = db.getTable(tableName);
        Column column = table.getColumns().get(0);

        assertEquals(ColumnType.CharType, column.getColumnType());
    }

    @Test
    public void createTableWithPictureColumn() {
        String dbName = "My DB";
        Controller controller = new Controller();
        controller.createDatabase(dbName);
        Database db = controller.getDatabase(dbName);
        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key", ColumnType.IntegerType, true);
        config.addColumn("Picture column", ColumnType.PictureType);
        db.addTable(tableName, config);

        Table table = db.getTable(tableName);
        Column column = table.getColumns().get(1);

        assertEquals(ColumnType.PictureType, column.getColumnType());
    }

    @Test(expected = NoPrimaryKeyExcpetion.class)
    public void createTableWithNoKey() {
        String dbName = "My DB";
        String tableName = "My table";
        Controller controller = new Controller();
        controller.createDatabase(dbName);
        Database db = controller.getDatabase(dbName);

        TableConfig tableConfig = new TableConfig();
        tableConfig.addColumn("id", ColumnType.IntegerType, false);
        db.addTable(tableName, tableConfig);
    }

    @Test(expected = IllegalPrimaryKeyException.class)
    public void createTableConfigWithPrimaryKeyOnPictureColumn() {
        TableConfig config = new TableConfig();
        config.addColumn("Picture column", ColumnType.PictureType, true);
    }
}