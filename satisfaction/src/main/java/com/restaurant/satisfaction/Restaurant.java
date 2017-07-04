package com.restaurant.satisfaction;

import com.restaurant.satisfaction.service.api.ItemComparator;
import com.restaurant.satisfaction.service.api.TextFileResourceLoaderService;
import com.restaurant.satisfaction.vo.Item;
import com.restaurant.satisfaction.vo.SatisfactionKnapSockResult;
import com.restaurant.satisfaction.vo.SatisfactionResult;
import com.restaurant.satisfaction.vo.TimeAndCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Created by Thofiq.Khan on 7/4/2017.
 */
@RestController
@RequestMapping(path = "/restaurant")
public class Restaurant {

    @Autowired(required = true)
    @Qualifier(value = "textFileResourceLoaderService")
    private TextFileResourceLoaderService textFileResourceLoaderService;

    /**
     * Max satisfaction by time slicing
     * Sort the items by time in ascending order and calculate the max satisfaction
     * @return
     */
    @RequestMapping(path = "/maxSatisfaction")
    public SatisfactionResult findMaxRestuarantSatisfaction(){

        int maxSatisfaction = 0, consumedTime = 0, itemAte = 0;

        List<Item> itemsLoadedFromTextFile = textFileResourceLoaderService.getItemsLoadedFromTextFile();

        //Get the time and count from text file
        TimeAndCount timeAndCountFromTextFile = textFileResourceLoaderService.getTimeAndCountFromTextFile();

        //Sort based on time taken - greedy algorithm time slicing
        Collections.sort(itemsLoadedFromTextFile, new ItemComparator());

        //Check the time given is more than the consumed time
        for(Item item : itemsLoadedFromTextFile) {
            if (timeAndCountFromTextFile.getTimeInMin() > consumedTime) {
                maxSatisfaction = maxSatisfaction + item.getAmount_of_satisfaction();
                consumedTime = consumedTime + item.getTime_taken_in_min();
                itemAte++;
            }
        }

        SatisfactionResult satisfactionResult = new SatisfactionResult();
        satisfactionResult.setSatisfaction(maxSatisfaction);
        satisfactionResult.setItemAte(itemAte);
        return satisfactionResult;
    }

    /**
     * Method which returns max satisfaction by knapsock algo
     * @return
     */
    @RequestMapping("/maxSatisfaction/knapsock")
    public SatisfactionKnapSockResult getMaxSatisfactionByKnapsock(){
        SatisfactionKnapSockResult satisfactionKnapSockResult = new SatisfactionKnapSockResult();
        satisfactionKnapSockResult.setMaxSatisfaction(this.textFileResourceLoaderService.getMaxSatisfactionUsingKnapsack());
        return satisfactionKnapSockResult;
    }
}
