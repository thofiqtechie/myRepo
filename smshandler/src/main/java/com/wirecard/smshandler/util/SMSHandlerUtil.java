package com.wirecard.smshandler.util;

import com.wirecard.smshandler.services.cache.SMSHandlerCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by thofi_000 on 11/18/2017.
 */
@Component
public class SMSHandlerUtil {

    @Autowired
    private SMSHandlerCache smsHandlerCache;

    /**
     * Get the message value from messages property file
     * @param key
     * @return
     */
    public String getValueFromMessageProperty(String key){
        return smsHandlerCache.messages.get(key);
    }

    /**
     * Get the service id to route
     * @param serviceCommand to identify service id in sms handler module
     * @return
     */
    public String getServiceIdFromServiceProperty(String serviceCommand){
        return smsHandlerCache.services.get(serviceCommand);
    }

    /**
     * Get the service id to route
     * @param serviceId : unique identifying service id in the sms handler module
     * @return
     */
    public String getServiceClassFromServiceClassProperty(String serviceId){
        return smsHandlerCache.serviceClasses.get(serviceId);
    }

    /**
     * @param value
     * @param delimeter
     * @return tokens
     */
    public String[] splitStringUsingDelimeter(String value, String delimeter){
        return value.split(delimeter);
    }
}
