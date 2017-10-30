package orb.corba;


/**
* orb/corba/RmiDatabasePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from orb/corba2/RmiDatabase.idl
* Monday, October 30, 2017 at 5:28:05 PM Eastern European Standard Time
*/

public abstract class RmiDatabasePOA extends org.omg.PortableServer.Servant
 implements orb.corba.RmiDatabaseOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_getName", new java.lang.Integer (0));
    _methods.put ("_get_getTableNames", new java.lang.Integer (1));
    _methods.put ("getTable", new java.lang.Integer (2));
    _methods.put ("subtract", new java.lang.Integer (3));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // orb/corba/RmiDatabase/_get_getName
       {
         String $result = null;
         $result = this.getName ();
         out = $rh.createReply();
         org.omg.CORBA.WStringValueHelper.write (out, $result);
         break;
       }

       case 1:  // orb/corba/RmiDatabase/_get_getTableNames
       {
         String $result = null;
         $result = this.getTableNames ();
         out = $rh.createReply();
         org.omg.CORBA.WStringValueHelper.write (out, $result);
         break;
       }

       case 2:  // orb/corba/RmiDatabase/getTable
       {
         String arg0 = org.omg.CORBA.WStringValueHelper.read (in);
         String $result = null;
         $result = this.getTable (arg0);
         out = $rh.createReply();
         org.omg.CORBA.WStringValueHelper.write (out, $result);
         break;
       }

       case 3:  // orb/corba/RmiDatabase/subtract
       {
         String arg0 = org.omg.CORBA.WStringValueHelper.read (in);
         String arg1 = org.omg.CORBA.WStringValueHelper.read (in);
         String $result = null;
         $result = this.subtract (arg0, arg1);
         out = $rh.createReply();
         org.omg.CORBA.WStringValueHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "RMI:orb.corba.RmiDatabase:0000000000000000"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public RmiDatabase _this() 
  {
    return RmiDatabaseHelper.narrow(
    super._this_object());
  }

  public RmiDatabase _this(org.omg.CORBA.ORB orb) 
  {
    return RmiDatabaseHelper.narrow(
    super._this_object(orb));
  }


} // class RmiDatabasePOA
