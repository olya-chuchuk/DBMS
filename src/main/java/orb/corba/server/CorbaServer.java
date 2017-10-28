package orb.corba.server;

import domain.Database;
import domain.Table;
import orb.corba.RmiDatabase;
import orb.corba.RmiDatabaseHelper;
import orb.corba.RmiDatabasePOA;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import repository.DatabaseRepository;
import repository.InMemDatabaseRepository;

import java.rmi.RemoteException;

public class CorbaServer extends RmiDatabasePOA {

    private DatabaseRepository repository;

    public CorbaServer() throws RemoteException {
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
        Database database = repository.getCurrentDatabase();
        Table table1 = database.getTable(tableName1);
        Table table2 = database.getTable(tableName2);
        return table1.subtract(table2).toString();
    }

    public static void main(String[] args) {

        if(System.getSecurityManager() != null) {
            System.setSecurityManager(null);
        }
        try {
            ORB orb = ORB.init(args, null);
            POA rootPoa = POAHelper.narrow(
                    orb.resolve_initial_references("RootPOA"));
            rootPoa.the_POAManager().activate();
            CorbaServer database = new CorbaServer();
            Object ref = rootPoa.servant_to_reference(database);
            RmiDatabase href = RmiDatabaseHelper.narrow(ref);
            Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "Database";
            NameComponent[] path = ncRef.to_name(name);
            ncRef.rebind(path, href);
            System.out.println("Working");
            orb.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
