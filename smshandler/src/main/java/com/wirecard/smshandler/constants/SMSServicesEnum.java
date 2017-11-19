package com.wirecard.smshandler.constants;

/**
 * Created by thofi_000 on 11/19/2017.
 */
public enum SMSServicesEnum {

    BALANCE_SERVICE("000001"), SEND_SERVICE("000002"), TOTAL_SERVICE("000003");

    private String serviceId;

    SMSServicesEnum(String serviceId){
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return serviceId;
    }
}
