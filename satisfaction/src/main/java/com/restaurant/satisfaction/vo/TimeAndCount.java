package com.restaurant.satisfaction.vo;

import org.springframework.stereotype.Component;

/**
 * Created by Thofiq.Khan on 7/4/2017.
 */
public class TimeAndCount {

    private int timeInMin;
    private int count;

    public int getTimeInMin() {
        return timeInMin;
    }

    public void setTimeInMin(int timeInMin) {
        this.timeInMin = timeInMin;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
