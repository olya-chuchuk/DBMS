package orb.corba;


/**
* orb/corba/_RmiDatabaseStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from orb/corba2/RmiDatabase.idl
* Monday, October 30, 2017 at 5:28:05 PM Eastern European Standard Time
*/

public class _RmiDatabaseStub extends org.omg.CORBA.portable.ObjectImpl implements orb.corba.RmiDatabase
{

  public String getName ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_getName", true);
                $in = _invoke ($out);
                String $result = org.omg.CORBA.WStringValueHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getName (        );
            } finally {
                _releaseReply ($in);
            }
  } // getName

  public String getTableNames ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_getTableNames", true);
                $in = _invoke ($out);
                String $result = org.omg.CORBA.WStringValueHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getTableNames (        );
            } finally {
                _releaseReply ($in);
            }
  } // getTableNames

  public String getTable (String arg0)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getTable", true);
                org.omg.CORBA.WStringValueHelper.write ($out, arg0);
                $in = _invoke ($out);
                String $result = org.omg.CORBA.WStringValueHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getTable (arg0        );
            } finally {
                _releaseReply ($in);
            }
  } // getTable

  public String subtract (String arg0, String arg1)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("subtract", true);
                org.omg.CORBA.WStringValueHelper.write ($out, arg0);
                org.omg.CORBA.WStringValueHelper.write ($out, arg1);
                $in = _invoke ($out);
                String $result = org.omg.CORBA.WStringValueHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return subtract (arg0, arg1        );
            } finally {
                _releaseReply ($in);
            }
  } // subtract

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "RMI:orb.corba.RmiDatabase:0000000000000000"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _RmiDatabaseStub
