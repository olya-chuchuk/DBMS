package web;

import domain.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import repository.DatabaseRepository;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Olha Chuchuk on 03.10.2017.
 */
@Controller
public class DatabaseController {

    @Autowired
    DatabaseRepository databaseRepository;

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
        model.addAttribute(table);
        return "show_table";
    }
}
