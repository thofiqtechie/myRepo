package test;

import java.util.Scanner;

/**
 * Created by Thofiq.Khan on 8/1/2017
 */
public class FindTheWinner {

    public static String MARIA = "Maria", ANDREA = "Andrea",ODD = "Odd", EVEN = "Even", EMPTY_STRING = "", TIE="Tie";

    /**
     * Main
     * @param args
     */
    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number for andrea");
        int n = in.nextInt();
        System.out.println("Enter the andrea array value");
        int[] andra_ar = new int[n];
        for(int ar_i = 0; ar_i < n; ar_i++){
            andra_ar[ar_i] = in.nextInt();
        }

        System.out.println("Enter the number for andrea");
        int maria = in.nextInt();
        System.out.println("Enter the andrea array value");
        int[] maria_ar = new int[maria];
        for(int ar_i = 0; ar_i < n; ar_i++){
            maria_ar[ar_i] = in.nextInt();
        }

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the game type EVEN/ODD");
        String input = keyboard.nextLine();
        System.out.println(winner(andra_ar, maria_ar, input));
    }

    /**
     * Winner method to declare the winner based on computation
     * @param andrea array of input values
     * @param maria array of input values
     * @param s ODD or Even
     * @return winner name Andrea/Maria
     */
    static String winner(int[] andrea, int[] maria, String s) {
        int andreaSum = 0, mariaSum = 0;

        //Length of both the array should be equal and should not be empty
        if (andrea.length > 0 && maria.length > 0 && andrea.length == maria.length) {
            int startValueForLoop = 0, incrementValue = 2;
            //Find starting value of i to loop the elements
            if (s.equals(ODD)) {
                startValueForLoop = 1;
            } else if (s.equals(EVEN)) {
                startValueForLoop = 0;
            }
            for (int i = startValueForLoop; i < andrea.length; ) {
                andreaSum = andreaSum + (andrea[i] - maria[i]);
                mariaSum = mariaSum + (maria[i]-andrea[i]);
                //To skip odd or even result
                i = i + incrementValue;
            }
        }

        if(andreaSum != 0 && mariaSum != 0) {
            if (andreaSum > mariaSum) {
                return ANDREA;
            } else if (andreaSum < mariaSum) {
                return MARIA;
            } else if (andreaSum == mariaSum) {
                return TIE;
            }
        }

        return EMPTY_STRING;
    }
}
