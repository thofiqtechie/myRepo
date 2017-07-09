package com.custom.hashamp.service;

import org.springframework.cloud.servicebroker.model.*;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Component;

@Component
public class CustomHashMapServiceInstanceService implements ServiceInstanceService {
	
    @Override
    public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest req) {
        String serviceInstanceId = req.getServiceInstanceId();      
        return new CreateServiceInstanceResponse();
    }

    @Override
    public GetLastServiceOperationResponse getLastOperation(GetLastServiceOperationRequest req) {
        String serviceInstanceId = req.getServiceInstanceId();        
        return new GetLastServiceOperationResponse();
    }

    @Override
    public DeleteServiceInstanceResponse deleteServiceInstance(DeleteServiceInstanceRequest req) {
        String serviceInstanceId = req.getServiceInstanceId();        
        return new DeleteServiceInstanceResponse();
    }

    @Override
    public UpdateServiceInstanceResponse updateServiceInstance(UpdateServiceInstanceRequest req) {
        String serviceInstanceId = req.getServiceInstanceId();        
        return new UpdateServiceInstanceResponse();
    }
}
