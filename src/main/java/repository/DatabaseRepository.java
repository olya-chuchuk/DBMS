package repository;

import domain.Database;
import domain.TableConfig;
import org.springframework.stereotype.Component;

/**
 * Created by Olha Chuchuk on 03.10.2017.
 */
public interface DatabaseRepository {
    void createEmptyDatabase(String dbName);
    void setCurrentDatabase(Database db);
    Database getCurrentDatabase();
    TableConfig getTableConfig();
    void setEmptyTableConfig();
}
