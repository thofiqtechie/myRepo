package test;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Thofiq.Khan on 8/1/2017
 */
public class PsychormeticTesting {

    public static int[] scores = {4,8,7}, lowerLimits = {2,4}, upperLimits = {8,4};

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        System.out.println("Enter size of scores");
        int scoreSize = in.nextInt();
        System.out.println("Enter score elements");
        int[] scores = new int[scoreSize];
        for(int ar_i = 0; ar_i < scoreSize; ar_i++){
            scores[ar_i] = in.nextInt();
        }

        System.out.println("Enter size of lowerLimits");
        int lowerLimitSize = in.nextInt();
        System.out.println("Enter lowerLimits elements");
        int[] lowerLimits = new int[lowerLimitSize];
        for(int ar_i = 0; ar_i < lowerLimitSize; ar_i++){
            lowerLimits[ar_i] = in.nextInt();
        }

        System.out.println("Enter size of upperLimits");
        int upperLimitsSize = in.nextInt();
        System.out.println("Enter upperLimits elements");
        int[] upperLimits = new int[lowerLimitSize];
        for(int ar_i = 0; ar_i < upperLimitsSize; ar_i++){
            upperLimits[ar_i] = in.nextInt();
        }

        for(int value : jobOffers(scores, lowerLimits, upperLimits)){
            System.out.println(value);
        }

    }

    /**
     * Return job offers
     * @param scores
     * @param lowerLimits
     * @param upperLimits
     * @return
     */
    static int[] jobOffers(int[] scores, int[] lowerLimits, int[] upperLimits){

        //Assumption - Both lower and upper limit should have same no of arguments
        int lowerLimitLength = lowerLimits.length;
        int upperLimitLength = upperLimits.length;
        List<Integer> resultList = new LinkedList<Integer>(); // To maintain the order using linked list
        if(lowerLimitLength != 0 && upperLimitLength != 0 && lowerLimitLength == upperLimitLength){
            for(int i = 0; i<lowerLimitLength; i++){
                int lowerLimitElement = lowerLimits[i];
                int upperLimitElement = upperLimits[i];

                //TODO Scanning on complete array required to incorporate binary search or some other algorithm
                int elementCount = 0;
                for(int j = 0; j<scores.length; j++){
                    if(scores[j] >= lowerLimitElement && scores[j] <= upperLimitElement){
                        elementCount++;
                    }
                }
                resultList.add(elementCount);
            }

            int[] resultArray = new int[resultList.size()];
            for(int i =0; i<resultList.size(); i++){
                resultArray[i] = resultList.get(i);
            }
            return resultArray;
        }

        //Return empty array if no value
        return new int[0];

    }
}
