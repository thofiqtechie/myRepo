package com.custom.hashamp.service;

import org.springframework.cloud.servicebroker.model.CreateServiceInstanceAppBindingResponse;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class CustomHashMapServiceInstanceBindingService implements ServiceInstanceBindingService {

    @Override
    public CreateServiceInstanceBindingResponse createServiceInstanceBinding(CreateServiceInstanceBindingRequest req) {
        String serviceInstanceId = req.getServiceInstanceId();
        String bindingId = req.getBindingId();      
        Map<String, Object> credentials = new HashMap<String, Object>() {{
            put("url", "http://thofiqhashmap.cfapps.io/");
            //Dummy service so removing the user name and password
            //put("username", UUID.randomUUID());
            //put("password", UUID.randomUUID());
        }};
        return new CreateServiceInstanceAppBindingResponse().withCredentials(credentials);
    }

    @Override
    public void deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest req) {
        String serviceInstanceId = req.getServiceInstanceId();
        String bindingId = req.getBindingId();        
    }
}
