package orb.rmi_corba;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Database extends Remote {
    String getName() throws RemoteException;
    String getTableNames() throws RemoteException;
    String getTable(String tableName) throws RemoteException;
    String subtract(String tableName1, String tableName2) throws RemoteException;
}
