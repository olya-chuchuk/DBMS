package rest;

import com.sun.rowset.internal.Row;
import domain.ColumnType;
import domain.Database;
import domain.Table;
import domain.TableConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import repository.DatabaseRepository;

import java.util.List;

/**
 * Created by Olha Chuchuk on 24.10.2017.
 */
@RestController
public class RestDatabaseController {

    @Autowired
    private DatabaseRepository repository;

    @PutMapping("/config")
    public void createNewConfigAjax() {
        repository.setEmptyTableConfig();
    }

    @PostMapping("/config")
    public void addColumnAjax(@RequestParam("columnName") String columnName,
                              @RequestParam("columnType") String columnType) {
        repository.getTableConfig().addColumn(columnName, ColumnType.valueOf(columnType));
    }
    @GetMapping("/database")
    public Database getDatabase() {
        return repository.getCurrentDatabase();
    }

    @GetMapping("/tables/{table_name}")
    public Table getTable(@PathVariable("table_name") String tableName) {
        return repository.getCurrentDatabase().getTable(tableName);
    }

    @GetMapping("/tables")
    public Table subtractTables(@RequestParam("subtract1") String tableName1,
                                @RequestParam("subtract2") String tableName2) {
        Database database = repository.getCurrentDatabase();
        Table table1 = database.getTable(tableName1);
        Table table2 = database.getTable(tableName2);
        return table1.subtract(table2);
    }

    @GetMapping("/tables/{table_name}/rows/{id}")
    public List<String> getRow(@PathVariable("table_name") String tableName,
                               @PathVariable("id") String rowId) {
        return repository.getCurrentDatabase().getTable(tableName).getRow(rowId);
    }

    @PutMapping("/tables/{table_name}/rows/{id}")
    public Table createOrUpdateRow(@RequestBody List<String> row,
                                  @PathVariable("table_name") String tableName,
                                  @PathVariable("id") String rowId) {
        Table table = repository.getCurrentDatabase().getTable(tableName);
        if(table.containsKey(rowId)) {
            table.updateRow(rowId, row);
        } else {
            table.addRow(row);
        }
        return table;
    }

    //create new table by configutation
    @PostMapping("/tables/{table_name}")
    public Table getConfiguration(@PathVariable("table_name") String tableName,
                                  @RequestBody TableConfig config) {
        Database database = repository.getCurrentDatabase();
        database.addTable(tableName, config);
        return database.getTable(tableName);
    }

    @DeleteMapping("/tables/{table_name}")
    public Database deleteTable(@PathVariable("table_name") String tableName) {
        Database database = repository.getCurrentDatabase();
        database.deleteTable(tableName);
        return database;
    }

    //create new db
    @PostMapping("/databases/{db_name}")
    public Database createNewDb(@PathVariable("db_name") String databaseName) {
        repository.createEmptyDatabase(databaseName);
        return repository.getCurrentDatabase();
    }

//    @GetMapping("/")
//    public TableConfig tableConfig() {
//        TableConfig config = new TableConfig();
//        config.addColumn("Primary key column", ColumnType.IntegerType, true);
//        config.addColumn("Integer column", ColumnType.IntegerType, false);
//        config.addColumn("Char column", ColumnType.CharType, false);
//        config.addColumn("Real column", ColumnType.RealType, false);
//        config.addColumn("Picture column", ColumnType.PictureType, false);
//        config.setKeyColumn(0);
//        return config;
//    }

    @ExceptionHandler({Exception.class})
    public ExceptionDto handleException(Exception ex) {
        return new ExceptionDto(ex);
    }
}

class ExceptionDto {
    private Class<?> type;
    private String message;
    public ExceptionDto(Exception ex) {
        type = ex.getClass();
        message = ex.getMessage();
    }

    public Class<?> getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
