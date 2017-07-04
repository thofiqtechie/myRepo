package com.restaurant.satisfaction.service.impl;

import com.restaurant.satisfaction.service.api.TextFileResourceLoaderService;
import com.restaurant.satisfaction.service.util.KnapsackUtil;
import com.restaurant.satisfaction.vo.Item;
import com.restaurant.satisfaction.vo.TimeAndCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Thofiq.Khan on 7/4/2017.
 */
@Service("textFileResourceLoaderService")
public class TextFileResourceLoaderServiceImpl implements TextFileResourceLoaderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextFileResourceLoaderServiceImpl.class);

    @Autowired
    private KnapsackUtil knapsackUtil;

    private ResourceLoader resourceLoader;

    private List<Item> items = new LinkedList<>();

    private List<Integer> satisfactionInputDetails = new ArrayList();
    private List<Integer> timeInputDetails = new ArrayList<>();

    public static final String BLANK_SPACE = "\\s";

    private TimeAndCount timeAndCount = new TimeAndCount();

    private boolean firstLineRead = false;

    @Autowired
    public TextFileResourceLoaderServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void init(){
        Resource resource = resourceLoader.getResource("classpath:data.txt");
        String line;
        try{
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                String[] split = line.split(BLANK_SPACE);

                if(split.length == 2) {

                    int splitFirstToken = Integer.parseInt(split[0]);
                    int splitSecondToken = Integer.parseInt(split[1]);

                    //First line in text file
                    if (!firstLineRead) {
                        timeAndCount.setTimeInMin(splitFirstToken);
                        timeAndCount.setCount(splitSecondToken);
                        firstLineRead = true;
                        continue;
                    }

                    //Construct item object and store in list
                    Item item = new Item();
                    item.setAmount_of_satisfaction(splitFirstToken);
                    this.satisfactionInputDetails.add(splitFirstToken);

                    item.setTime_taken_in_min(splitSecondToken);
                    this.timeInputDetails.add(splitSecondToken);

                    items.add(item);
                }
            }
            br.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public List<Item> getItemsLoadedFromTextFile() {
        return this.items;
    }

    @Override
    public TimeAndCount getTimeAndCountFromTextFile(){
        return this.timeAndCount;
    }

    /**
     * Method uses knapsack algo to get the max satisfaction
     * @return
     */
    public int getMaxSatisfactionUsingKnapsack(){
        Integer[] satisfactionInputDetailsInArray = this.satisfactionInputDetails.toArray(new Integer[satisfactionInputDetails.size()]);
        Integer[] timeInputDetailsInArray = this.timeInputDetails.toArray(new Integer[this.timeInputDetails.size()]);
        return KnapsackUtil.performKnapsockAlgo(satisfactionInputDetailsInArray, timeInputDetailsInArray, timeAndCount.getTimeInMin());
    }

}
