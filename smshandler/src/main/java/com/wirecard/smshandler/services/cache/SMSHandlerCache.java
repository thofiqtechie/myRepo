package com.wirecard.smshandler.services.cache;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by thofi_000 on 11/18/2017.
 * To load property file values of sms handler service configuration during server start up
 *
 * Note:
 * Actual configuration can be done at data base side also to reduce no of app server restart or
 * we can make reloadable property file to keep changes effected for new services
 */
@Configuration
public class SMSHandlerCache {

    private static Logger log = Logger.getLogger(SMSHandlerCache.class);

    public HashMap<String, String> services = new HashMap<>();
    public HashMap<String, String> messages = new HashMap<>();
    public HashMap<String, String> serviceClasses = new HashMap<>();

    public static final String serviceProperty = "serviceconfig";
    public static final String messageProperty = "messages";
    public static final String serviceClassProperty = "serviceclass";

    @PostConstruct
    public void constructPropertyValues(){

        log.info("Initializing property files.......");

        this.services = getPropertyValues(serviceProperty);
        log.info("Loaded service property file "+services);

        this.messages = getPropertyValues(messageProperty);
        log.info("Loaded messages property file "+messages);

        this.serviceClasses = getPropertyValues(serviceClassProperty);
        log.info("Loaded service classes property file "+serviceClasses);
    }

    /**
     * Load the property from the given property file
     * @param propertyFileName
     * @return
     */
    public HashMap getPropertyValues(String propertyFileName) {

        ResourceBundle bundle = ResourceBundle.getBundle(propertyFileName);
        Enumeration<String> keys = bundle.getKeys();

        HashMap<String, String> propertyValues = new HashMap<>();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            propertyValues.put(key, bundle.getString(key));
        }

        return propertyValues;
    }
}
