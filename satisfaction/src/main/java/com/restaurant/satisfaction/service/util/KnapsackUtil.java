package com.restaurant.satisfaction.service.util;

import org.springframework.stereotype.Component;

/**
 * Created by thofiq_000 on 7/5/2017.
 */
@Component
public class KnapsackUtil {

    public static int performKnapsockAlgo(Integer[] satisfactionInputDetails, Integer[] timeInputDetails, int maxTimeGiven) {

        int itemCount = timeInputDetails.length;
        int[][] multiDimenMatrix = new int[itemCount + 1][maxTimeGiven + 1];

        //Making initial set as 0,0
        for (int col = 0; col <= maxTimeGiven; col++) {
            multiDimenMatrix[0][col] = 0;
        }

        for (int row = 0; row <= itemCount; row++) {
            multiDimenMatrix[row][0] = 0;
        }

        //looping through the items and forming matrix
        for (int item=1;item<=itemCount;item++){
            for (int inputTime=1;inputTime <= maxTimeGiven;inputTime++){
                if (timeInputDetails[item-1]<=inputTime){
                    multiDimenMatrix[item][inputTime]=Math.max (satisfactionInputDetails[item-1]+multiDimenMatrix[item-1][inputTime-timeInputDetails[item-1]], multiDimenMatrix[item-1][inputTime]);
                }
                else {
                    multiDimenMatrix[item][inputTime]=multiDimenMatrix[item-1][inputTime];
                }
            }
        }

        return multiDimenMatrix[itemCount][maxTimeGiven];
    }
}
