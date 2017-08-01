package test;

/**
 * Created by Thofiq.Khan on 8/1/2017
 */
public class InTheFuture {

    public static void main(String[] args){
        System.out.println(minNum(3,5,1));
        System.out.println(minNum(4,5,1));
    }

    /**
     * @param asha_No_Prob_PerDay Asha probs/day
     * @param kelly_No_Prob_PerDay Kelly probs/day
     * @param no_Of_Prob_Asha_Ahead_Of_Kelly Asha prob ahead of Kelly
     * @return Min num of days kelly required to suppress Asha
     */
    static int minNum(int asha_No_Prob_PerDay, int kelly_No_Prob_PerDay, int no_Of_Prob_Asha_Ahead_Of_Kelly){

        int min_Num_Of_Days_Kelly_Required_To_Ahead_Of_Asha = 0, initial_process_day = 1;

        //As per constraint
        if(asha_No_Prob_PerDay == 0 || kelly_No_Prob_PerDay == 0 || no_Of_Prob_Asha_Ahead_Of_Kelly > 100){
            System.out.println("Invalid input !!!!");
        }else if(asha_No_Prob_PerDay < kelly_No_Prob_PerDay){
            int total_Asha_no_prob_PerDay = asha_No_Prob_PerDay + no_Of_Prob_Asha_Ahead_Of_Kelly;

            //If number of probs are equal then we don't have to process just increase 1 and relax
            if(total_Asha_no_prob_PerDay == kelly_No_Prob_PerDay){
                min_Num_Of_Days_Kelly_Required_To_Ahead_Of_Asha = initial_process_day + 1;
            }

            if(total_Asha_no_prob_PerDay < kelly_No_Prob_PerDay){
                min_Num_Of_Days_Kelly_Required_To_Ahead_Of_Asha = initial_process_day;
            }

            //Need to process the days here
            if(total_Asha_no_prob_PerDay > kelly_No_Prob_PerDay){
                int ashaNoDays = total_Asha_no_prob_PerDay;
                int kellyNoDays = kelly_No_Prob_PerDay;
                while(ashaNoDays >= kellyNoDays){
                    min_Num_Of_Days_Kelly_Required_To_Ahead_Of_Asha++;
                    ashaNoDays = ashaNoDays + asha_No_Prob_PerDay;
                    kellyNoDays = kellyNoDays + kelly_No_Prob_PerDay;
                }
                min_Num_Of_Days_Kelly_Required_To_Ahead_Of_Asha += initial_process_day;
            }
        }else{
            System.out.println("Kelly can never suppress Asha bcz the no of prob asha solve per day will be alyz more");
            min_Num_Of_Days_Kelly_Required_To_Ahead_Of_Asha = -1;
        }

        return min_Num_Of_Days_Kelly_Required_To_Ahead_Of_Asha;
    }
}
