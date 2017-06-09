import java.io.*;

public class Lab2 {
    public static void main(String[] args)throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            Integer nullInt = null;
            nullInt.intValue();
        } catch (NullPointerException o){
            System.out.print("It's a NullPointerException!");
        }
    }
}