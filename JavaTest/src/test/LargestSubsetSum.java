package test;

import java.util.*;

/**
 * Created by Thofiq.Khan on 8/1/2017.
 */
public class LargestSubsetSum {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        for(int ar_i = 0; ar_i < n; ar_i++){
            ar[ar_i] = in.nextInt();
        }
        long[] resultArray = maxSubsetSum(ar);

        for(int l =0; l<resultArray.length ; l++){
            System.out.println(resultArray[l]);
        }
    }

    /**
     * Calculate max sub array value
     * @param inputArray
     * @return
     */
    static long[] maxSubsetSum(int[] inputArray){
        long[] resultArray = new long[inputArray.length];

        for(int i = 0;i<inputArray.length; i++){

            //Get each element of input array and process the element
            int inputArrayElement = inputArray[i];

            //Creating new array to form subsets
            int [] inputArrayEleArray = new int[inputArrayElement];
            for(int j =1;j<=inputArrayElement;j++){
                inputArrayEleArray[j-1] = j;
            }
            List<List<Integer>> inputArrayElementSubset = subsets(inputArrayEleArray);
            List<Integer> resultList = new LinkedList<>();

            //Loop the subset list and process the logic accordingly
            for(List inputArrayElementSubsetEle : inputArrayElementSubset){

                //Find the lcm of subset
                int [] findLcmForThisArray = new int[inputArrayElementSubsetEle.size()];
                for(int k =0;k<inputArrayElementSubsetEle.size(); k++){
                    findLcmForThisArray[k] = (int)inputArrayElementSubsetEle.get(k);
                }
                if(findLcmForThisArray.length != 0) {
                    int subsetLcm = lcm(findLcmForThisArray, findLcmForThisArray.length);

                    //If lcm is equal to input element sum the subset element value
                    if (subsetLcm == inputArrayElement) {
                        int sumOfSubset = 0;
                        for (int value : findLcmForThisArray) {
                            sumOfSubset = sumOfSubset + value;
                        }
                        resultList.add(sumOfSubset);
                    }
                }
            }

            //Extract max value from result list
            Integer maxSubSetValue = Collections.max(resultList);

            resultArray[i] = maxSubSetValue;
        }

        return resultArray;
    }

    /**
     * LargestSubsetSum Calculation
     * @param arr
     * @param n
     * @return
     */
    static int lcm(int arr[], int n)
    {
        int lcm = arr[0];
        for (int i=1; i<n; i++)
            lcm = ( ((arr[i]*lcm)) /
                    (gcd(arr[i], lcm)) );
        return lcm;
    }

    /**
     * GCD calculation
     * @param a
     * @param b
     * @return
     */
    static int gcd(int a, int b)
    {
        if (b==0) return a;
        return gcd(b, a%b);
    }

    /**
     * Return all subset for given set using recursive algorithm
     * @param inputArray input array which is combination input given as array
     * @return
     */
    public static List<List<Integer>> subsets(int[] inputArray) {
        ArrayList<List<Integer>> resultList = new ArrayList<List<Integer>>();
        if(inputArray.length == 0){
            resultList.add(new ArrayList<Integer>());
            return resultList;
        }
        Arrays.sort(inputArray);
        int head = inputArray[0];
        int[] rest = new int[inputArray.length-1];
        System.arraycopy(inputArray, 1, rest, 0, inputArray.length-1);
        for(List<Integer> list : subsets(rest)){
            List<Integer> newList = new LinkedList<>();
            newList.add(head);
            newList.addAll(list);
            resultList.add(list);
            resultList.add(newList);
        }
        return resultList;
    }
}
