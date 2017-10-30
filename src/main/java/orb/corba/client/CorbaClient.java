package orb.corba.client;

import orb.corba.RmiDatabase;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import orb.corba.RmiDatabaseHelper;

import java.util.Scanner;

public class CorbaClient {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "Database";
            RmiDatabase database = RmiDatabaseHelper.narrow(ncRef.resolve_str(name));

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
