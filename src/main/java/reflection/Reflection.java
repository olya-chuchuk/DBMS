package reflection;

import domain.Database;
import domain.Table;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.DatabaseRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Reflection {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ApplicationContext context = new AnnotationConfigApplicationContext(config.DatabaseConfig.class);
        DatabaseRepository repository = context.getBean(DatabaseRepository.class);
        Scanner sc = new Scanner(System.in);

        Database database = repository.getCurrentDatabase();
        System.out.println("List of available tables:");
        database.getTableNames()
                .stream()
                .forEach(System.out::println);

        System.out.println("Please, enter table name:");
        String tableName = sc.nextLine();
        Table table = database.getTable(tableName);

        System.out.println("List of available methods:");
        Method[] methods = table.getClass().getMethods();
        IntStream.range(0, methods.length)
                .forEach(i -> System.out.println(i + " " + methods[i].getReturnType()
                        + " " + methods[i].getName()
                        + " " + Arrays.toString(methods[i].getParameters())));

        boolean cont = true;
        while(cont) {
            System.out.println("Please, enter the number of method:");
            int methodNumber = sc.nextInt();
            Method method = methods[methodNumber];

            System.out.println("List of parameters:");

            int paramsSize = method.getParameterCount();
            Object[] params = new Object[paramsSize];
            for (int rem = paramsSize; rem > 0; --rem) {
                System.out.println("Remaining number of parameters: " + rem);
                System.out.println("Please, enter the parameter");
                String parameter = sc.next();
                params[paramsSize - rem] = parameter;
            }

            Object res = method.invoke(table, params);

            System.out.println("The result:");
            System.out.println(res != null ? res.toString(): "void");

            System.out.println("Want to proceed? [Y,n]");
            String ans = sc.next();
            if(!ans.toLowerCase().equals("y")) {
                cont = false;
            }
        }
    }
}
