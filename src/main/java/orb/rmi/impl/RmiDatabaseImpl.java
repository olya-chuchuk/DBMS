package orb.rmi.impl;

import orb.rmi.RmiDatabase;
import repository.DatabaseRepository;
import repository.InMemDatabaseRepository;

import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;

public class RmiDatabaseImpl extends RemoteObject implements RmiDatabase{

    private DatabaseRepository repository;

    public RmiDatabaseImpl() throws RemoteException {
        super();
        repository = new InMemDatabaseRepository();
    }

    @Override
    public String getName() throws RemoteException {
        return repository.getCurrentDatabase().getName();
    }
}
