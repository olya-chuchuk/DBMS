package config;

import domain.Database;
import domain.Table;
import domain.TableConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import repository.DatabaseRepository;
import repository.InMemDatabaseRepository;

/**
 * Created by Olha Chuchuk on 03.10.2017.
 */
@Configuration
public class DatabaseConfig {

    @Bean
    @Scope("prototype")
    public Database database(String name) {
        return new Database(name);
    }

    @Bean
    public DatabaseRepository repository() {
        return new InMemDatabaseRepository();
    }

}
