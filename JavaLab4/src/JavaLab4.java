/**
 * Created by EW043872 on 9/19/2015.
 */
import java.io.*;
public class JavaLab4 {
    public static void main(String[] args)throws FileNotFoundException, IOException, ClassNotFoundException {
        int i = -1;
        int j = 5;
        String s = new String ("Boort");
        String t = null;

        try {
            f(i, s);//examines the case where i < 0
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            f(j,t);//examines the case where s is null
        } catch (ArithmeticException a){
            a.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            f(j,s);//examines the case where all parameters are correct
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void f(int i, String s) throws Exception{
        if (i < 0){
            ArithmeticException a = new ArithmeticException("Parameter 'i' less than 0");
            throw a;
        }
        else if (s == null){
            Exception e = new Exception("String 's' cannot be null");
            throw e;
        }

    }
}
