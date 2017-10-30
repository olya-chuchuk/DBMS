package orb.rmi.jrmp.server;

import domain.Database;
import domain.Table;
import orb.rmi.RmiDatabase;
import repository.DatabaseRepository;
import repository.InMemDatabaseRepository;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RmiServer extends RemoteObject implements RmiDatabase{

    private DatabaseRepository repository;

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
            RmiDatabase stub = (RmiDatabase)
                    UnicastRemoteObject.exportObject(database,0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("Working");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
