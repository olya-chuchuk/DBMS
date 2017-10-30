package orb.rmi.iiop.client;

import orb.rmi.RmiDatabase;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Scanner;

public class RmiClient {

    public static void main(String[] args) {
        String name = "Database";
        try {
            Context context = new InitialContext();
            Object obj = context.lookup(name);
            RmiDatabase database = (RmiDatabase) PortableRemoteObject.narrow(obj, RmiDatabase.class);

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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
