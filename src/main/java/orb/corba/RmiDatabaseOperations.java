package orb.corba;


/**
* orb/corba/RmiDatabaseOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from orb/corba/RmiDatabase.idl
* 28 ������� 2017 �. 15:49:48 EEST
*/

public interface RmiDatabaseOperations 
{
  String getName();
  String getTableNames();
  String getTable(String arg0);
  String subtract(String arg0, String arg1);
} // interface RmiDatabaseOperations