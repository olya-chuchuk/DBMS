Web application - uses Spring, jsp pages(src/main/java/web, src/main/webapp packages).
maven - jetty:run
localhost:8082/dbms/web/index - starting page. All other links are through buttons.
If you do not want an empty database, use url localhost:8082/dbms/web/database before creating new db.
Files are saved in format .db.
List of filenames on index page is the list of *.db file in root directory of the project.

Rest web service - also uses Spring (src/main/java/rest package)
When you run web application, rest url are of format localhost:8082/dbms/rest
list of links can be found in RestDatabaseController class.

Reflection (src/main/java/reflection package):
Uses Spring to create all beans.
1. run Reflection class - choose which table you want to use, then which operation you want to apply.
Choose only methods with parameters of primitive types or Strings.
Otherwise you won't be able to type the parameters.

RMI using rmiregister(src/main/java/rmi package):
1. start rmiregister (type this command in console in folder /target/classes)
2. run RmiServer - should print working to console
3. run RmiClient - shows dbname, list of tables and prints specified table

RMI/IIOP (src/main/java/rmi RmiDatabase & /iiop package):
0. generate RmiServer_Stub.class
    from folder /target/classes type in cmd
     "rmic -iiop orb.rmi.iiop.server.RmiServer"
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

AJAX ("/web/create_table_ajax" , webapp/js folder):
Start web application
Go to page "/web/create_table_ajax" and construct table

Google App Engine
Rest web server is available on URL dbms-184410.appspot.com
(e.g. http://dbms-184410.appspot.com/rest/database)
Dashboard URL https://console.cloud.google.com/home/dashboard
App Engine -> Versions
---------------------------------------------------
How I deployed it:
1. mvn archetype:generate
    -Dappengine-version=1.9.17
    -Dappengine-id=dbms
    -Dfilter=com.google.appengine.archetypes:
    (web project with name dbms was generated in current folder,
     in folder WEB-INF new files appengine-web.xml and logging.properties)
2. Локально запустить и проверить
    mvn appengine:devserver -Dappengine.port=8080
3. Развернуть на удаленном сервере
    mvn appengine:update
    (нужно быть залогиненым + будет пытаться запушить в сервер, id
    которого указан в appengine-web.xml "<application>dbms-184410</application>")

RMI/IIOP - CORBA (src/main/java/orb/rmi_corba package):
0. compile classes
1. from console cd taget/classes
2. rmic -iiop orb.rmi_corba.RmiServer
3. orbd -ORBInitialPort 1050
4. run RmiServer with
    (-Djava.naming.factory.initial=com.sun.jndi.cosnaming.CNCtxFactory
    -Djava.naming.provider.url=iiop://localhost:1050) VM Options
5. run RmiClient with
(-ORBInitialPort 1050 -ORBInitialHost localhost) program arguments

