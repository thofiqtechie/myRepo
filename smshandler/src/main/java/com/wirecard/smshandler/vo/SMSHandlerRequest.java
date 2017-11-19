package com.wirecard.smshandler.vo;

import java.io.Serializable;

/**
 * Created by thofi_000 on 11/18/2017.
 * SMS Handler request to post the information to sms handler module by external systems
 */
public class SMSHandlerRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String smsContent;
    private String senderDeviceId;

    /**
     * No arg constructor for request posting
     */
    public SMSHandlerRequest(){}


    /**
     * To make external request
     * @param smsContent
     * @param senderDeviceId
     */
    public SMSHandlerRequest(String smsContent, String senderDeviceId) {
        this.smsContent = smsContent;
        this.senderDeviceId = senderDeviceId;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getSenderDeviceId() {
        return senderDeviceId;
    }

    public void setSenderDeviceId(String senderDeviceId) {
        this.senderDeviceId = senderDeviceId;
    }

    @Override
    public String toString() {
        return "SMSHandlerRequest{" +
                "smsContent='" + smsContent + '\'' +
                ", senderDeviceId='" + senderDeviceId + '\'' +
                '}';
    }
}
