package orb.rmi;

import domain.Table;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RmiDatabase extends Remote {
    public String getName() throws RemoteException;
    public List<String> getTableNames() throws RemoteException;
    public Table getTable(String tableName) throws RemoteException;
}