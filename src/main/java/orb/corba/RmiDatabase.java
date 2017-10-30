package orb.corba;

import javax.rmi.PortableRemoteObject;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiDatabase extends Remote, RmiDatabaseOperations, org.omg.CORBA.Object, org.omg.CORBA.portable.IDLEntity  {
//    String getName() throws RemoteException;
//    String getTableNames() throws RemoteException;
//    String getTable(String tableName) throws RemoteException;
//    String subtract(String tableName1, String tableName2) throws RemoteException;

}
