import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by ERIC WILSON on 8/28/2015.
 */
public class Lab1 {
    public static void main(String[] args) {
        //PART 1
        Scanner br = new Scanner(System.in);

        System.out.print("Part 1:\nWhat is your name?");
        String name = null;
        name = br.nextLine();
        System.out.print("Hello, " + name + "!\n");

        //PART 2
        System.out.print("Part2:\n");
        int [] arr = {1, 2, 3, 4, 5, 6};

        //print original array
        System.out.print("Original Array: ");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i]);
        System.out.print('\n');

        reverse(arr);

        //print reversed array
        System.out.print("Reversed Array: ");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i]);
        System.out.print('\n');

        //PART 3
        int gridSize = 0;
        System.out.print("Part 3:\nGrid Size: ");
        gridSize = br.nextInt();

        printGrid(gridSize);

        System.out.print('\n');

        //EXTRA CREDIT
        System.out.print("Extra Credit: ");
        String [] xtraCredArray = {"dorp", "blergh", "dishtowel", "kitten", "cheese", "REAAAAAARWWWWWWW!!!", "pope", "bort"};

        //print initial unfiltered array
        System.out.print("\nInitial Array:  ");
        for (int o = 0; o < xtraCredArray.length; o++)
            System.out.print(xtraCredArray[o] + ' ');
        //filter that array
        String [] filteredArray = FilterByLength(xtraCredArray, 4);
        //print filtered array
        System.out.print("\nFiltered Array: ");
        for (int o = 0; o < filteredArray.length; o++)
            System.out.print(filteredArray[o] + ' ');
    }

    public static void reverse (int [] intArr){
        int temp = 0;
        for (int i = 0; i < intArr.length / 2; i++ ){
            //iterations swap values from the outside in
            temp = intArr[i];
            intArr[i] = intArr[intArr.length - 1 - i];
            intArr[intArr.length - i - 1] = temp;
        }
    }

    public static void printGrid (int size){
        for (int i = 0; i < size; i++){
            System.out.print('+');
            for (int j = 0; j < size; j++)
                System.out.print("--+");
            System.out.print("\n|");
            for (int k = 0; k < size; k++)
                System.out.print("  |");
            System.out.print('\n');
        }
        System.out.print('+');
        for (int l = 0; l < size; l++)
            System.out.print("--+");
    }

    public static String[] FilterByLength(String[] s, int i){
        //count the number of items we're keeping
        int count = 0;
        for (int j = 0; j < s.length; j++ ){
            if (s[j].length() != i)
                count++;
        }

        //just in case we don't have to remove anything
        if (count == s.length)
            return s;
        else {
            String[] newStrArr = new String[count];
            int pos = 0;
            for (int j = 0; j < s.length; j++) {
                if (s[j].length() != i) newStrArr[pos++] = s[j];

            }
            return newStrArr;
        }
    }
}
