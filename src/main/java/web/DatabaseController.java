package web;

import domain.ColumnType;
import domain.Database;
import domain.Table;
import domain.TableConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import repository.DatabaseRepository;

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

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/database")
    public String database(Model model) {
        model.addAttribute(databaseRepository.getCurrentDatabase().getTableList());
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

    @GetMapping("/add_row")
    public String addRow(Model model) {
        model.addAttribute("columns", currentTable.getColumns());
        return "add_row";
    }
}
