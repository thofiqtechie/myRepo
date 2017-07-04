package com.restaurant.satisfaction.service.api;

import com.restaurant.satisfaction.vo.Item;

import java.util.Comparator;

/**
 * Created by Thofiq.Khan on 7/4/2017.
 */
public class ItemComparator implements Comparator<Item> {

    @Override
    public int compare(Item item1, Item item2) {
        if(item1.getTime_taken_in_min()==item2.getTime_taken_in_min())
            return 0;
        else if(item1.getTime_taken_in_min() > item2.getTime_taken_in_min())
            return 1;
        else
            return -1;
    }
}
