import java.nio.file.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by EW043872 on 12/7/2015.
 */
public class FunctionalWhoozawhatsit {
    public static String getRevenue(String s) {
        return s.split("[|]")[2]; // split will split a string into an array of strings based on given regular expression
    }

    private static String getName(String s) {
        return s.split("[|]")[0];
    }

    private static String getStudio (String s){
        return s.split("[|]")[1];
    }

    private static String getYear (String s) {
        return s.split("[|]")[3];
    }

    public static void main(String [] args) throws Exception{
        Path path = Paths.get("C:\\Users\\EW043872\\IdeaProjects\\Functional\\src\\data.txt");
        Stream<String> line = Files.lines(path);

        Double seventies = line
                .filter((ln1 -> Integer.parseInt(getYear(ln1)) >= 1970))
                .filter(ln2 -> Integer.parseInt(getYear(ln2)) < 1980)
                .mapToDouble(ln -> Double.parseDouble(getRevenue(ln)))
                .sum();
        System.out.print("Seventies: " + seventies + "\n");

        line = Files.lines(path);

        Double eighties = line
                .filter((ln1 -> Integer.parseInt(getYear(ln1)) >= 1980))
                .filter(ln2 -> Integer.parseInt(getYear(ln2)) < 1990)
                .mapToDouble(ln -> Double.parseDouble(getRevenue(ln)))
                .sum();
        System.out.print("Eighties: " + eighties + "\n");

        if (seventies > eighties)
            System.out.print("Seventies wins!");
        else if (eighties > seventies)
            System.out.print("Eighties wins!");
    }
}
