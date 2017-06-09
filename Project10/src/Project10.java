/**
 * Created by EW043872 on 11/1/2015.
 */
import acme.NetworkService;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public class Project10 {
    public static void main(String[] args) throws NoSuchFieldException,
            SecurityException, IllegalArgumentException, IllegalAccessException
    {

        NetworkService ns = new NetworkService();
        Class targetClass = ns.getClass();
        Field fields[] = targetClass.getDeclaredFields();
        System.out.println("\nFields:");
        for (Field field : fields) {
            System.out.println(field.getName() + " is: "
                    + field.toGenericString());
        }
        Field field = ns.getClass().getDeclaredField("machineName");
        field.setAccessible(true);
        field.set(ns, "aws.com");

        ns.connect();

    }
}
