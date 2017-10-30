package orb.rmi_corba;

import domain.Table;
import repository.DatabaseRepository;
import repository.InMemDatabaseRepository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

public class RmiServer extends PortableRemoteObject implements Database {
    private DatabaseRepository repository;

    public RmiServer() throws RemoteException {
        super();
        repository = new InMemDatabaseRepository();
    }

    @Override
    public String getName() {
        return repository.getCurrentDatabase().getName();
    }

    @Override
    public String getTableNames() {
        return repository.getCurrentDatabase().getTableNames().toString();
    }

    @Override
    public String getTable(String tableName) {
        return repository.getCurrentDatabase().getTable(tableName).toString();
    }

    @Override
    public String subtract(String tableName1, String tableName2) {
        domain.Database database = repository.getCurrentDatabase();
        Table table1 = database.getTable(tableName1);
        Table table2 = database.getTable(tableName2);
        return table1.subtract(table2).toString();
    }

    public static void main(String[] args) {
        try {
            String name = "Database";
            Database database = new RmiServer();
            Context context = new InitialContext();
            context.rebind(name, database);
            System.out.println("Working");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
