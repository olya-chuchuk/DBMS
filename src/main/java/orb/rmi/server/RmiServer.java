package orb.rmi.server;

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
