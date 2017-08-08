package test;

import java.util.Scanner;

/**
 * Created by Thofiq.Khan on 8/1/2017
 */
public class ConsecutiveSum {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number");
        System.out.println(ConsecutiveSum.consecutive(in.nextInt()));
    }

    /**
     *
     * @param num to find consecutive sum
     * @return no of count the sum occurred
     */
    public static int consecutive(long num){
        int resultCount = 0;
        //Constraint
        if(num < 1 || num > Math.pow(10,12)){
            System.out.println("Invalid input !!!!!");
            return resultCount;
        }
        //If num less than 2 can't calculate the consecutive pattern
        else if(num <= 2){
            return resultCount;
        }else{
            for(int i=1; i< num ;i++){
                int tempSum = 0;
                for(int j=i; j< num; j++){
                    tempSum = tempSum + j;
                    if(tempSum == num) {
                        resultCount++;
                        break;
                    }
                }
            }
            return resultCount;
        }
    }
}
