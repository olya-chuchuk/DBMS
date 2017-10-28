//package orb.corba.server;
//
//import domain.Database;
//import domain.Table;
//import orb.corba.RmiDatabase;
//import repository.DatabaseRepository;
//import repository.InMemDatabaseRepository;
//
//import javax.rmi.PortableRemoteObject;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.rmi.server.UnicastRemoteObject;
//
//public class RmiServer extends PortableRemoteObject implements RmiDatabase {
//
//    private DatabaseRepository repository;
//
//    public RmiServer() throws RemoteException {
//        //super();
//        repository = new InMemDatabaseRepository();
//    }
//
//    @Override
//    public String getName() throws RemoteException {
//        return repository.getCurrentDatabase().getName();
//    }
//
//    @Override
//    public String getTableNames() throws RemoteException {
//        return repository.getCurrentDatabase().getTableNames().toString();
//    }
//
//    @Override
//    public String getTable(String tableName) throws RemoteException {
//        return repository.getCurrentDatabase().getTable(tableName).toString();
//    }
//
//    @Override
//    public String subtract(String tableName1, String tableName2) throws RemoteException {
//        Database database = repository.getCurrentDatabase();
//        Table table1 = database.getTable(tableName1);
//        Table table2 = database.getTable(tableName2);
//        return table1.subtract(table2).toString();
//    }
//
//    public static void main(String[] args) {
//        try {
//            String name = "Database";
//            RmiDatabase database = new RmiServer();
//            RmiDatabase stub = (RmiDatabase)
//                    UnicastRemoteObject.exportObject(database,0);
//            Registry registry = LocateRegistry.getRegistry();
//            registry.rebind(name, stub);
//            System.out.println("Working");
//        } catch (RemoteException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}