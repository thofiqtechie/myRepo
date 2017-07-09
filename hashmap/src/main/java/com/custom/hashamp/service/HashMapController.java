package com.custom.hashamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by thofi_000 on 7/9/2017.
 */
@RestController
@RequestMapping("/hash")
public class HashMapController {

    //Using custom hashmap class
    @Autowired
    public HashMapService hashMapService;

    @RequestMapping(value = "/{key}", method = RequestMethod.PUT)
    public ResponseEntity<String> put(@PathVariable("key") String key,
                                      @RequestBody String value) {
        hashMapService.customHashMap.put(key, value);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public ResponseEntity<Object> get(@PathVariable("key") String key) {
        Object result = hashMapService.customHashMap.get(key);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("{}", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> remove(@PathVariable("key") String key) {
        Object result = hashMapService.customHashMap.remove(key);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("{}", HttpStatus.NOT_FOUND);
        }
    }

}
