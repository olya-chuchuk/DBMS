package repository;

import domain.ColumnType;
import domain.Database;
import domain.Table;
import domain.TableConfig;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Olha Chuchuk on 03.10.2017.
 */
public class InMemDatabaseRepository implements DatabaseRepository {

    private Database database;
    private TableConfig config;

    public InMemDatabaseRepository() {

        String dbName = "My database";
        database = new Database(dbName);

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

        database.addTable(tableName + "1", config);
        database.addTable(tableName + "2", config);
        Table table1 = database.getTable(tableName + "1");
        Table table2 = database.getTable(tableName + "2");
        table1.addRow(row1);
        table1.addRow(row2);
        table2.addRow(row1);
        table2.addRow(row3);
    }

    @Override
    public void createEmptyDatabase(String dbName) {
        database = new Database(dbName);
    }

    @Override
    public void setCurrentDatabase(Database db) {
        database = db;
    }

    @Override
    public Database getCurrentDatabase() {
        return database;
    }

    @Override
    public TableConfig getTableConfig() {
        return config;
    }

    @Override
    public void setEmptyTableConfig() {
        config = new TableConfig();
    }
}
