//package orb.corba.client;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.rmi.PortableRemoteObject;
//
//public class RmiClient {
//    public static void main(String[] args) {
//        try {
//            if(System.getSecurityManager() != null) {
//                System.setSecurityManager(null);
//            }
//            Context context = new InitialContext();
//            String name = "Database";
//            Object object = context.lookup(name);
//            System.out.println(object.getClass());
//
//            System.out.println(PortableRemoteObject.narrow(object, RmiDatabase.class).getClass());
//            RmiDatabase database = (RmiDatabase)
//                    PortableRemoteObject.narrow(object, RmiDatabase.class);
//
//
//            System.out.println(database.getName());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
