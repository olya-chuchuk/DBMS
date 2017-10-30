RMI using rmiregister(src/main/java/rmi package):
1. start rmiregister (type this command in console in folder /target/classes)
2. run RmiServer - should print working to console
3. run RmiClient - shows dbname, list of tables and prints specified table

Reflection (src/main/java/reflection package):
Uses Spring to create all beans.
1. run Reflection class - choose which table you want to use, then which operation you want to apply.
Choose only methods with parameters of primitive types or Strings.
Otherwise you won't be able to type the parameters.

Web application - uses Spring, jsp pages(src/main/java/web, src/main/webapp packages).
maven - jetty:run
localhost:8082/dbms/web/index - starting page. All other links are through buttons.
If you do not want an empty database, use url localhost:8082/dbms/web/database before creating new db.
Files are saved in format .db.
List of filenames on index page is the list of *.db file in root directory of the project.

Rest web service - also uses Spring (src/main/java/rest package)
When you run web application, rest url are of format localhost:8082/dbms/rest
list of links can be found in RestDatabaseController class.

RMI/IIOP (src/main/java/rmi RmiDatabase & /iiop package):
1. start orbd -ORBInitialPort 1050 (type this command in console from folder /target/classes)
2. run RmiServer - should print "Working" to console
	VM options
	-Djava.naming.factory.initial=com.sun.jndi.cosnaming.CNCtxFactory
	-Djava.naming.provider.url=iiop://localhost:1050
3. run RmiClient - shows dbname, list of tables and prints specified table
	VM options
	-Djava.naming.factory.initial=com.sun.jndi.cosnaming.CNCtxFactory
	-Djava.naming.provider.url=iiop://localhost:1050

CORBA (src/main/java/orb/corba package):
1. orbd -ORBInitialPost 1050
2. run CorbaServer with (-ORBInitialPort 1050 -ORBInitialHost localhost) program arguments
3. run CorbaClient with (-ORBInitialPort 1050 -ORBInitialHost localhost) program arguments

CORBA - RMI/IIOP (src/main/java/orb/corba package):
1. orbd -ORBInitialPost 1050
2. run CorbaServer with (-ORBInitialPort 1050 -ORBInitialHost localhost) program arguments
3. run RmiClient with (-Djava.naming.factory.initial=com.sun.jndi.cosnaming.CNCtxFactory
    -Djava.naming.provider.url=iiop://localhost:1050t) VM Options

AJAX ("/web/create_table_ajax" , webapp/js folder):
Go to page "/web/create_table_ajax" and construct table
