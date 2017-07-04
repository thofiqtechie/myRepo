package com.restaurant.satisfaction.service.impl;

import com.restaurant.satisfaction.service.api.TextFileResourceLoaderService;
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

    private ResourceLoader resourceLoader;

    //To store in proper sequence to remove first entry which is not item
    private List<Item> items = new LinkedList<>();

    public static String BLANK_SPACE = "\\s";

    private TimeAndCount timeAndCount = new TimeAndCount();

    private boolean firstLineRead = false;

    @Autowired
    public TextFileResourceLoaderServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void init() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:data.txt");
        String line;
        try{
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                String[] split = line.split(BLANK_SPACE);

                //First line in text file
                if(!firstLineRead) {
                    timeAndCount.setTimeInMin(Integer.parseInt(split[0]));
                    timeAndCount.setCount(Integer.parseInt(split[1]));
                    firstLineRead = true;
                    continue;
                }

                //Construct item object and store in list
                Item item = new Item();
                item.setAmount_of_satisfaction(Integer.parseInt(split[0]));
                item.setTime_taken_in_min(Integer.parseInt(split[1]));
                items.add(item);
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
}
