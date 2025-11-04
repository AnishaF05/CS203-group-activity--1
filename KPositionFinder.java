import java.util.Scanner;

public class KPositionFinder {
    //partitioning algorithm
    public static int performPartitioning( int[] A, int left, int right){
        int k = A[left]; // initialize the pivot
        int s = left; // initialize the last index of subarray <k

        for (int i = left + 1; i <= right; i++) {
            //if the current item is less than k
            if ( A[i] < k ){
                s++; // expand the subarray
                // swap the item with k
                int temp = A[s];
                A[s] = A[i];
                A[i] = temp;
            }
        }
        // swap k with item at A[s] and return index of k
        int temp = A[left];
        A[left] = A[s];
        A[s] = temp;
        return s;
    }
    public static void main (String [] args){
        Scanner scanner = new Scanner (System.in);
        System.out.println("Enter an array separated with spaces (eg 1 2 3): ");
        String inputArray  = scanner.nextLine();

        String[] stringNumbers = inputArray.split(" ");

        //transform user input into an array
        int[] userArray = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            userArray[i] = Integer.parseInt(stringNumbers[i]);
        }

        //use the partitioning alg on the array
        int positionOfK = performPartitioning(userArray, 0, userArray.length -1 );

        //print out the array and position and value of k
        System.out.println("Here is your array: ");
        for (int element : userArray) {
            System.out.print(element + " ");
        }

        System.out.println("\nIndex of k: " + positionOfK);
        System.out.println("Value of k: " + userArray[positionOfK]);
        System.out.println();

        scanner.close();
    }
}
