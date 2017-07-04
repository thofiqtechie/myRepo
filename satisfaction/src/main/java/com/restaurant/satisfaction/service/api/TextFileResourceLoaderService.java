package com.restaurant.satisfaction.service.api;

import com.restaurant.satisfaction.vo.Item;
import com.restaurant.satisfaction.vo.TimeAndCount;

import java.util.List;
import java.util.Map;

/**
 * Created by Thofiq.Khan on 7/4/2017.
 */
public interface TextFileResourceLoaderService {

    public List<Item> getItemsLoadedFromTextFile();

    public TimeAndCount getTimeAndCountFromTextFile();
}
