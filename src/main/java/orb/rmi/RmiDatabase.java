package orb.rmi;

import domain.Table;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RmiDatabase extends Remote {
    String getName() throws RemoteException;
    List<String> getTableNames() throws RemoteException;
    Table getTable(String tableName) throws RemoteException;
    Table subtract(String tableName1, String tableName2) throws RemoteException;
}