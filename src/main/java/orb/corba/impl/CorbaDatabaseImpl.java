package orb.corba.impl;

import orb.corba.java.CorbaDatabasePOA;
import repository.DatabaseRepository;
import repository.InMemDatabaseRepository;

public class CorbaDatabaseImpl extends CorbaDatabasePOA {

    private DatabaseRepository repository;

    public CorbaDatabaseImpl() {
        repository = new InMemDatabaseRepository();
    }

    @Override
    public String getName() {
        return repository.getCurrentDatabase().getName();
    }
}
