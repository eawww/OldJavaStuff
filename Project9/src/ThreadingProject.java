import java.util.ArrayList;
import java.util.Random;

/**
 * Created by EW043872 on 10/23/2015.
 */
public class ThreadingProject extends Thread {
    int[][] matrix = new int[30][1000000];
    int x; //used to determine which thing will be thinging
    int total; //holds the total for each thread

    //This would probably be better if I only passed individual rows.
    public ThreadingProject(int[][] passedMatrix, int passedX){
        matrix = passedMatrix;
        //x is the row that this instance of the object will be working on
        x = passedX;
    }

    //actually does the calculations
    @Override
    public void run() {
        for (int y = 0; y < matrix[x].length; y++) {
            total = total + (int)Math.log(matrix[x][y]);
        }
    }

    //for adding individual totals to the megaSum
    public int getTotal(){
        return total;
    }

    //calculates all of the rows
    public int logSum(){
        int sum = 0;
        for (int x = 0; x < matrix.length ; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                sum = sum + (int)Math.log(matrix[x][y]);
            }
        }
        return sum;
    }



//MAINMAINMAIN
    public static void main(String args[]){
        int[][] mainMatrix = new int[30][1000000];
        Random rand = new Random();
        // Initialize matrix with random numbers
        for (int x = 0; x < mainMatrix.length; x++) {
            for (int y = 0; y < mainMatrix[x].length; y++) {
                int randomNum = rand.nextInt(200); // 0 - 199
                mainMatrix[x][y] = randomNum;
            }
        }
        //Single threaded portion
        ThreadingProject tp = new ThreadingProject(mainMatrix, -1);
        System.out.print("matrix.length " + tp.matrix.length + "\n");
        long startTime = System.nanoTime();
        System.out.print("Logsum single thread = " + tp.logSum() + "\n");
        long endTime = System.nanoTime();
        System.out.println("Computation took " + ((endTime - startTime) / 1000000)
                + " milliseconds\n");

        //multithreaded portion
        int megaSum = 0;
        //Declare an array of thready objects and
        //give them their row assignments
       ArrayList<ThreadingProject> arr = new ArrayList<ThreadingProject>();

        for (int t = 0; t < mainMatrix.length; t++){
            arr.add(t, new ThreadingProject(mainMatrix, t));
        }

        //GO!
        startTime = System.nanoTime();
        //start all threads
        for(int i =0; i < mainMatrix.length; i++){
            arr.get(i).start();
        }
        //wait for threads to die and then harvest their sweet data
        for(int i =0; i < mainMatrix.length; i++){
            try {
                arr.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            megaSum += arr.get(i).getTotal();
        }
        //aaaaaand... TIME!
        endTime = System.nanoTime();
        System.out.print("Logsum multi thread = " + megaSum + "\n");
        System.out.println("Computation took " + ((endTime - startTime) / 1000000)
                + " milliseconds");



    }
}


