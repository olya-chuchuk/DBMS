package orb.rmi.jrmp.client;

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

            System.out.println("Choose 2 tables to subtract:");
            System.out.println("First:");
            String tableName1 = sc.nextLine();
            System.out.println("Second:");
            String tableName2 = sc.nextLine();
            System.out.println("Resulting table:");
            System.out.println(database.subtract(tableName1, tableName2));
        } catch (NotBoundException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
