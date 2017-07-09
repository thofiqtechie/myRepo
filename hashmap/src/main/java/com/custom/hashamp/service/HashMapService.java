package com.custom.hashamp.service;

import com.custom.hashamp.CustomHashMap;
import org.springframework.stereotype.Service;

/**
 * Created by thofi_000 on 7/9/2017.
 */
@Service
public class HashMapService {

    //Singleton object to store data
    public CustomHashMap customHashMap = new CustomHashMap();


}
