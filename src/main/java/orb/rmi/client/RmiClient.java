package orb.rmi.client;

import orb.rmi.RmiDatabase;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RmiClient {
    public static void main(String[] args) {
        try {
            String name = "Database";
            Registry registry = LocateRegistry.getRegistry();
            RmiDatabase database = (RmiDatabase) registry.lookup(name);
            System.out.println("Database name:" + database.getName());
            System.out.println("List of tables:" + database.getTableNames());
            System.out.println("Choose the table");
            Scanner sc = new Scanner(System.in);
            String tableName = sc.nextLine();
            System.out.println("Table " + tableName + ":");
            System.out.println(database.getTable(tableName));
        } catch (NotBoundException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
