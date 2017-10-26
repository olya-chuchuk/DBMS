package orb.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiDatabase extends Remote {
    public String getName() throws RemoteException;
}