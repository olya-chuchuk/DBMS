package orb.corba;

/**
* orb/corba/RmiDatabaseHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from orb/corba2/RmiDatabase.idl
* Monday, October 30, 2017 at 5:28:05 PM Eastern European Standard Time
*/

public final class RmiDatabaseHolder implements org.omg.CORBA.portable.Streamable
{
  public orb.corba.RmiDatabase value = null;

  public RmiDatabaseHolder ()
  {
  }

  public RmiDatabaseHolder (orb.corba.RmiDatabase initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = orb.corba.RmiDatabaseHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    orb.corba.RmiDatabaseHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return orb.corba.RmiDatabaseHelper.type ();
  }

}
