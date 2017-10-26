package domain;

import controller.Controller;
import exceptions.DuplicateKeyException;
import exceptions.IllegalRowException;
import exceptions.NoSuchRowException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olha_Chuchuk on 9/14/2017.
 */
public class CRUDTest {



    @Test
    public void addOneRowIntoTable() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.IntegerType, true);
        config.addColumn("Integer column", ColumnType.IntegerType, false);
        config.addColumn("Char column", ColumnType.CharType, false);
        config.addColumn("Real column", ColumnType.RealType, false);
        config.addColumn("Picture column", ColumnType.PictureType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row = Arrays.asList("1", "123", "a", "0.12345",
                "http://worldartsme.com/images/123-clipart-1.jpg");
        table.addRow(row);

        assertEquals(1, table.getRowsCount());
        assertEquals(row, table.getRows().get(0));
        assertEquals(row, table.getRow("1"));
    }

    @Test
    public void addRowsIntoTableWithIntPrimaryKey() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.IntegerType, true);
        config.addColumn("Integer column", ColumnType.IntegerType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row1 = Arrays.asList("1", "123");
        List<String> row2 = Arrays.asList("2", "123");
        List<String> row3 = Arrays.asList("3", "123");
        table.addRow(row1);
        table.addRow(row2);
        table.addRow(row3);

        assertEquals(3, table.getRowsCount());
        assertEquals(row1, table.getRow("1"));
        assertEquals(row2, table.getRow("2"));
        assertEquals(row3, table.getRow("3"));
    }

    @Test(expected = DuplicateKeyException.class)
    public void addRowsWithDuplicateKeysIntoTableWithIntPrimaryKey() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.IntegerType, true);
        config.addColumn("Integer column", ColumnType.IntegerType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row1 = Arrays.asList("1", "123");
        List<String> row2 = Arrays.asList("2", "234");
        List<String> row3 = Arrays.asList("1", "345");
        table.addRow(row1);
        table.addRow(row2);
        table.addRow(row3);
    }


    @Test
    public void addRowsIntoTableWithCharPrimaryKey() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.CharType, true);
        config.addColumn("Character column", ColumnType.CharType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row1 = Arrays.asList("a", "A");
        List<String> row2 = Arrays.asList("b", "B");
        List<String> row3 = Arrays.asList("c", "C");
        table.addRow(row1);
        table.addRow(row2);
        table.addRow(row3);

        assertEquals(3, table.getRowsCount());
        assertEquals(row1, table.getRow("a"));
        assertEquals(row2, table.getRow("b"));
        assertEquals(row3, table.getRow("c"));
    }

    @Test(expected = DuplicateKeyException.class)
    public void addRowsWithDuplicateKeysIntoTableWithCharPrimaryKey() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.CharType, true);
        config.addColumn("Character column", ColumnType.CharType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row1 = Arrays.asList("a", "A");
        List<String> row2 = Arrays.asList("b", "B");
        List<String> row3 = Arrays.asList("a", "C");
        table.addRow(row1);
        table.addRow(row2);
        table.addRow(row3);
    }

    @Test
    public void addRowsIntoTableWithRealPrimaryKey() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.RealType, true);
        config.addColumn("Real column", ColumnType.RealType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row1 = Arrays.asList("0.1", "0.11");
        List<String> row2 = Arrays.asList("0.2", "0.22");
        List<String> row3 = Arrays.asList("0.3", "0.33");
        table.addRow(row1);
        table.addRow(row2);
        table.addRow(row3);

        assertEquals(3, table.getRowsCount());
        assertEquals(row1, table.getRow("0.1"));
        assertEquals(row2, table.getRow("0.2"));
        assertEquals(row3, table.getRow("0.3"));
    }

    @Test(expected = DuplicateKeyException.class)
    public void addRowsWithDuplicateKeysIntoTableWithRealPrimaryKey() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.RealType, true);
        config.addColumn("Real column", ColumnType.RealType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row1 = Arrays.asList("0.1", "0.11");
        List<String> row2 = Arrays.asList("0.2", "0.22");
        List<String> row3 = Arrays.asList("0.1", "0.33");
        table.addRow(row1);
        table.addRow(row2);
        table.addRow(row3);
    }

    @Test(expected = IllegalRowException.class)
    public void addRowWithWrongNumberOfParameters() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.IntegerType, true);
        config.addColumn("Integer column", ColumnType.IntegerType, false);
        config.addColumn("Char column", ColumnType.CharType, false);
        config.addColumn("Real column", ColumnType.RealType, false);
        config.addColumn("Picture column", ColumnType.PictureType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row = Arrays.asList("1", "123", "a", "0.12345");
        table.addRow(row);
    }

    @Test(expected = IllegalRowException.class)
    public void addRowWithWrongIntColumn() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.IntegerType, true);
        config.addColumn("Integer column", ColumnType.IntegerType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row = Arrays.asList("1", "one");
        table.addRow(row);
    }

    @Test(expected = IllegalRowException.class)
    public void addRowWithWrongCharColumn() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.IntegerType, true);
        config.addColumn("Character column", ColumnType.CharType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row = Arrays.asList("1", "aa");
        table.addRow(row);
    }

    @Test(expected = IllegalRowException.class)
    public void addRowWithWrongRealColumn() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.IntegerType, true);
        config.addColumn("Real column", ColumnType.RealType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row = Arrays.asList("1", "1/2");
        table.addRow(row);
    }

    @Test
    public void readRowsFromTable() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.IntegerType, true);
        config.addColumn("Integer column", ColumnType.IntegerType, false);
        config.addColumn("Char column", ColumnType.CharType, false);
        config.addColumn("Real column", ColumnType.RealType, false);
        config.addColumn("Picture column", ColumnType.PictureType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row1 = Arrays.asList("1", "123", "a", "0.1",
                "http://worldartsme.com/images/123-clipart-1.jpg");
        List<String> row2 = Arrays.asList("2", "234", "b", "0.2",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/NY-234.svg/750px-NY-234.svg.png");
        List<String> row3 = Arrays.asList("3", "345", "c", "0.3",
                "http://www.zahlenparty.de/wp-content/uploads/345.jpg");
        table.addRow(row1);
        table.addRow(row2);
        table.addRow(row3);

        assertEquals(3, table.getRowsCount());
        assertEquals(3, table.getRows().size());
        assertEquals(row1, table.getRow("1"));
        assertEquals(row2, table.getRow("2"));
        assertEquals(row3, table.getRow("3"));
        System.out.println(table.getRows());
    }

    @Test
    public void updateRowNotPrimaryField() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.IntegerType, true);
        config.addColumn("Integer column", ColumnType.IntegerType, false);
        config.addColumn("Char column", ColumnType.CharType, false);
        config.addColumn("Real column", ColumnType.RealType, false);
        config.addColumn("Picture column", ColumnType.PictureType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row = Arrays.asList("1", "123", "a", "0.1",
                "http://worldartsme.com/images/123-clipart-1.jpg");
        List<String> newRow = Arrays.asList("1", "234", "b", "0.2",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/NY-234.svg/750px-NY-234.svg.png");
        table.addRow(row);
        table.updateRow("1", newRow);

        assertEquals(newRow, table.getRow("1"));
    }


    @Test
    public void updateRowPrimaryField() {
            Controller controller = new Controller();

            String dbName = "My database";
            controller.createDatabase(dbName);
            Database database = controller.getDatabase(dbName);

            String tableName = "My table";
            TableConfig config = new TableConfig();
            config.addColumn("Primary key column", ColumnType.IntegerType, true);
            config.addColumn("Integer column", ColumnType.IntegerType, false);
            config.addColumn("Char column", ColumnType.CharType, false);
            config.addColumn("Real column", ColumnType.RealType, false);
            config.addColumn("Picture column", ColumnType.PictureType, false);
            database.addTable(tableName, config);

            Table table = database.getTable(tableName);
            List<String> row = Arrays.asList("1", "123", "a", "0.1",
                    "http://worldartsme.com/images/123-clipart-1.jpg");
            List<String> newRow = Arrays.asList("2", "234", "b", "0.2",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/NY-234.svg/750px-NY-234.svg.png");
            table.addRow(row);
            table.updateRow("1", newRow);

            assertEquals(newRow, table.getRow("2"));
    }

    @Test(expected = DuplicateKeyException.class)
    public void updateRowWithDuplicateKey() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.IntegerType, true);
        config.addColumn("Integer column", ColumnType.IntegerType, false);
        config.addColumn("Char column", ColumnType.CharType, false);
        config.addColumn("Real column", ColumnType.RealType, false);
        config.addColumn("Picture column", ColumnType.PictureType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row1 = Arrays.asList("1", "123", "a", "0.1",
                "http://worldartsme.com/images/123-clipart-1.jpg");
        List<String> row2 = Arrays.asList("2", "234", "b", "0.2",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/NY-234.svg/750px-NY-234.svg.png");
        List<String> newRow = Arrays.asList("2", "123", "a", "0.1",
                "http://worldartsme.com/images/123-clipart-1.jpg");
        table.addRow(row1);
        table.addRow(row2);
        table.updateRow("1", newRow);
    }

    @Test(expected = IllegalRowException.class)
    public void updateRowWithWrongTypes() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.IntegerType, true);
        config.addColumn("Integer column", ColumnType.IntegerType, false);
        config.addColumn("Char column", ColumnType.CharType, false);
        config.addColumn("Real column", ColumnType.RealType, false);
        config.addColumn("Picture column", ColumnType.PictureType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row = Arrays.asList("1", "123", "a", "0.1",
                "http://worldartsme.com/images/123-clipart-1.jpg");
        List<String> newRow = Arrays.asList("1", "one", "b", "0.2",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/NY-234.svg/750px-NY-234.svg.png");
        table.addRow(row);
        table.updateRow("1", newRow);
    }

    @Test(expected = IllegalRowException.class)
    public void updateRowWithWrongNumberOfParameters() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.IntegerType, true);
        config.addColumn("Integer column", ColumnType.IntegerType, false);
        config.addColumn("Char column", ColumnType.CharType, false);
        config.addColumn("Real column", ColumnType.RealType, false);
        config.addColumn("Picture column", ColumnType.PictureType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row = Arrays.asList("1", "123", "a", "0.1",
                "http://worldartsme.com/images/123-clipart-1.jpg");
        List<String> newRow = Arrays.asList("1", "234", "b", "0.2");
        table.addRow(row);
        table.updateRow("1", newRow);
    }

    @Test(expected = NoSuchRowException.class)
    public void updateNonExistingRow() {
            Controller controller = new Controller();

            String dbName = "My database";
            controller.createDatabase(dbName);
            Database database = controller.getDatabase(dbName);

            String tableName = "My table";
            TableConfig config = new TableConfig();
            config.addColumn("Primary key column", ColumnType.IntegerType, true);
            config.addColumn("Integer column", ColumnType.IntegerType, false);
            config.addColumn("Char column", ColumnType.CharType, false);
            config.addColumn("Real column", ColumnType.RealType, false);
            config.addColumn("Picture column", ColumnType.PictureType, false);
            database.addTable(tableName, config);

            Table table = database.getTable(tableName);
            List<String> row = Arrays.asList("1", "123", "a", "0.1",
                    "http://worldartsme.com/images/123-clipart-1.jpg");
            List<String> newRow = Arrays.asList("1", "234", "b", "0.2");
            table.addRow(row);
            table.updateRow("2", newRow);
    }

    @Test
    public void deleteExistingRow() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.IntegerType, true);
        config.addColumn("Integer column", ColumnType.IntegerType, false);
        config.addColumn("Char column", ColumnType.CharType, false);
        config.addColumn("Real column", ColumnType.RealType, false);
        config.addColumn("Picture column", ColumnType.PictureType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row1 = Arrays.asList("1", "123", "a", "0.1",
                "http://worldartsme.com/images/123-clipart-1.jpg");
        List<String> row2 = Arrays.asList("2", "234", "b", "0.2",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/NY-234.svg/750px-NY-234.svg.png");
        table.addRow(row1);
        table.addRow(row2);
        table.deleteRow("1");

        assertEquals(1, table.getRowsCount());
        assertEquals(row2, table.getRow("2"));

    }

    @Test(expected = NoSuchRowException.class)
    public void deleteNonExistingRow() {
        Controller controller = new Controller();

        String dbName = "My database";
        controller.createDatabase(dbName);
        Database database = controller.getDatabase(dbName);

        String tableName = "My table";
        TableConfig config = new TableConfig();
        config.addColumn("Primary key column", ColumnType.IntegerType, true);
        config.addColumn("Integer column", ColumnType.IntegerType, false);
        config.addColumn("Char column", ColumnType.CharType, false);
        config.addColumn("Real column", ColumnType.RealType, false);
        config.addColumn("Picture column", ColumnType.PictureType, false);
        database.addTable(tableName, config);

        Table table = database.getTable(tableName);
        List<String> row = Arrays.asList("1", "123", "a", "0.1",
                "http://worldartsme.com/images/123-clipart-1.jpg");
        table.addRow(row);
        table.deleteRow("2");
    }

}
