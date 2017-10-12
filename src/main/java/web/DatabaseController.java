package web;

import domain.ColumnType;
import domain.Database;
import domain.Table;
import domain.TableConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repository.DatabaseRepository;
import util.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Olha Chuchuk on 03.10.2017.
 */
@Controller
public class DatabaseController {

    @Autowired
    private DatabaseRepository databaseRepository;
    private Database currentDatabase;
    private Table currentTable;
    private TableConfig config = new TableConfig();
    private String tablename = "";
    private String currentKey;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("files", FileCounter.getAllDbFiles());
        return "index";
    }

    @GetMapping("/database")
    public String database(Model model) {
        model.addAttribute("tableList", databaseRepository.getCurrentDatabase().getTableList());
        model.addAttribute("dbName", databaseRepository.getCurrentDatabase().getName());
        return "database";
    }

    @PostMapping("/create_database")
    public String createDatabase(HttpServletRequest request, Model model) {
        String databaseName = request.getParameter("databaseName");
        databaseRepository.createEmptyDatabase(databaseName);
        model.addAttribute("name", databaseName);
        return "redirect:/database";
    }

    @GetMapping("/show_table")
    public String showTable(HttpServletRequest request, Model model) {
        String tableName = request.getParameter("tableName");
        Table table = databaseRepository.getCurrentDatabase().getTable(tableName);
        if(table != null) {
            currentTable = table;
            model.addAttribute(currentTable);
        }
        return "show_table";
    }


    @GetMapping("/create_table")
    public String createTable(Model model) {
        model.addAttribute("config", config);
        return "create_table";
    }

    @PostMapping("/add_column")
    public String addColumn(@RequestParam("newColumnName") String name,
                            @RequestParam("newColumnType") String type) {
        ColumnType columnType = ColumnType.valueOf(type);
        config.addColumn(name, columnType);
        return "redirect:/create_table";
    }

    @PostMapping("/delete_table")
    public String deleteTalbe(@RequestParam("tableName") String tableName) {
        databaseRepository.getCurrentDatabase().deleteTable(tableName);
        return "redirect:/database";
    }

    @PostMapping("/update_row")
    public String updateRow(@RequestParam("tableName") String tableName,
                            @RequestParam("key") String key,
                            Model model) {
        currentTable = databaseRepository.getCurrentDatabase().getTable(tableName);
        currentKey = key;
        model.addAttribute("row", currentTable.getRow(key));
        model.addAttribute("columns", currentTable.getColumns());
        return "update_row";
    }

    @PostMapping("/set_primary_key")
    public String setPrimaryKey(@RequestParam("tableName") String name,
                                Model model) {
        tablename = name;
        model.addAttribute("config", config);
        return "set_primary_key";
    }

    @PostMapping("/create_constructed_table")
    public String createConstructedTable(@RequestParam("keyColumn") String key) {
        config.setPrimaryKey(Integer.valueOf(key));
        databaseRepository.getCurrentDatabase().addTable(tablename, config);
        tablename = "";
        config = new TableConfig();
        return "redirect:/database";
    }

    @PostMapping("/update_constructed_row")
    public String updateConstructedRow(HttpServletRequest request) {
        int colSize = currentTable.getColumns().size();
        List<String> row = new LinkedList<>();
        for(int i = 0; i < colSize; ++i) {
            row.add(request.getParameter(String.valueOf(i)));
        }
        currentTable.updateRow(currentKey, row);
        return "redirect:/show_table?tableName=" + currentTable.getName();
    }

    @PostMapping("/add_constructed_row")
    public String addRow(HttpServletRequest request, Model model) {
        int colSize = currentTable.getColumns().size();
        List<String> row = new LinkedList<>();
        for(int i = 0; i < colSize; ++i) {
            row.add(request.getParameter(String.valueOf(i)));
        }
        currentTable.addRow(row);
        return "redirect:/show_table?tableName=" + currentTable.getName();
    }

    @RequestMapping("/add_row")
    public String addRow(Model model) {
        model.addAttribute("columns", currentTable.getColumns());
        return "add_row";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String catchServerError(Model model, Exception ex) {
        model.addAttribute("exceptionMessage", ex.toString());
        return "error";
    }

    @PostMapping("/save_to_file")
    public String saveToFile(@RequestParam("fileName") String fileName) {
        Database database = databaseRepository.getCurrentDatabase();
        Database.serialize(database, fileName + ".db");
        return "redirect:/database";
    }

    @PostMapping("/upload_database")
    public String uploadFromFile(@RequestParam("fileName") String fileName) {
        Database database = Database.deserialize(fileName + ".db");
        databaseRepository.setCurrentDatabase(database);
        return "redirect:/database";
    }

    @PostMapping("/subtract_tables")
    public String subtractTables(Model model) {
        model.addAttribute("tables", databaseRepository.getCurrentDatabase().getTableNames());
        return "subtract_tables";
    }

    @PostMapping("/subtract_chosen_tables")
    public String subtractChosebTables(@RequestParam("table1") String tablename1,
                                       @RequestParam("table2") String tablename2,
                                       Model model) {
        Database database = databaseRepository.getCurrentDatabase();
        Table table1 = database.getTable(tablename1);
        Table table2 = database.getTable(tablename2);
        currentTable = table1.subtract(table2);
        model.addAttribute(currentTable);
        return "show_final_table";
    }
}
