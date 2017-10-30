package orb.rmi.iiop.server;

import domain.Database;
import domain.Table;
import orb.rmi.RmiDatabase;
import repository.DatabaseRepository;
import repository.InMemDatabaseRepository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;
import java.util.List;

public class RmiServer extends PortableRemoteObject implements RmiDatabase{

    private DatabaseRepository repository;

    /**
     * Initializes the object by calling <code>exportObject(this)</code>.
     *
     * @throws RemoteException if export fails.
     */
    public RmiServer() throws RemoteException {
        super();
        repository = new InMemDatabaseRepository();
    }

    @Override
    public String getName() throws RemoteException {
        return repository.getCurrentDatabase().getName();
    }

    @Override
    public List<String> getTableNames() throws RemoteException {
        return repository.getCurrentDatabase().getTableNames();
    }

    @Override
    public Table getTable(String tableName) throws RemoteException {
        return repository.getCurrentDatabase().getTable(tableName);
    }

    @Override
    public Table subtract(String tableName1, String tableName2) throws RemoteException {
        Database database = repository.getCurrentDatabase();
        Table table1 = database.getTable(tableName1);
        Table table2 = database.getTable(tableName2);
        Table res = table1.subtract(table2);
        return res;
    }

    public static void main(String[] args) {
        try {
            String name = "Database";
            RmiDatabase database = new RmiServer();
            Context context = new InitialContext();
            context.rebind(name, database);
            System.out.println("Working");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
